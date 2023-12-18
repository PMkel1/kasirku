package com.thoriq.kasirku.stock

import android.companion.CompanionDeviceManager
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.thoriq.kasirku.R
import com.thoriq.kasirku.cls.dialogKasirku
import com.thoriq.kasirku.database.ListDatabase
import com.thoriq.kasirku.database.listbarang.DatabaseDao
import com.thoriq.kasirku.database.listbarang.ListBarang
import com.thoriq.kasirku.databinding.FragmentStokUbahBinding
import java.io.ByteArrayOutputStream
import java.io.InputStream

class StockUbahFragment : Fragment() {

    lateinit var binding: FragmentStokUbahBinding
    lateinit var databaseDao: DatabaseDao
    private var pickImage = 100
    private var imageUri: Uri? = null
    private var imageContent: ByteArray? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStokUbahBinding.inflate(inflater, container, false)
        val application = requireNotNull(this.activity).application
        databaseDao = ListDatabase.getInstance(application).ListDatabaseDao
        val dialog = dialogKasirku(requireContext())
        var idBarang = requireArguments().getLong("idBarang")
        var listBarang = databaseDao.getById(idBarang)
        // Menampilkan data barang pada UI
        binding.namaItem.setText(listBarang.namaBarang)
        binding.hargabarang.setText(listBarang.harga.toString())
        binding.tipeBarang.setText(listBarang.tipeBarang)
        val bmp = listBarang.image?.let { BitmapFactory.decodeByteArray(listBarang.image, 0, it.size) }
        binding.gambarItemUbah.setImageBitmap(bmp)

        binding.buttonUbahItem.setOnClickListener {
            // Retrieve values from UI elements
            val nama = binding.namaItem.text.toString()
            val harga = binding.hargabarang.text.toString()
            val tipe = binding.tipeBarang.text.toString()
            val stock = 0
            if (nama.isEmpty() or harga.isEmpty() or tipe.isEmpty()) {
                dialog.dialogWarning("nama, harga, atau tipe tidak boleh kosong")
            } else if (harga.length < 3) {
                dialog.dialogWarning("harga minimal 3 digit ")
            } else if (harga.first().equals('0')) {
                dialog.dialogWarning("harga tidak boleh dimulai dengan 0")
            } else {
                val barang = ListBarang(listBarang.idBarang, nama, tipe, stock, harga.toDouble(),imageContent)
                databaseDao.update(barang)
                this.findNavController().navigate(R.id.action_stockUbahFragment_to_stockFragment)
            }
            binding.buttonBatal.setOnClickListener {
                this.findNavController().navigate(R.id.action_stockUbahFragment_to_stockFragment)
            }

        }
        binding.gambarItemUbah.setOnClickListener{
            val gallery = Intent(Intent.ACTION_GET_CONTENT)
            gallery.setType("image/*")
            startActivityForResult(gallery, pickImage)
        }
        return binding.root
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == CompanionDeviceManager.RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data
            val input: InputStream? = imageUri?.let {
                requireContext().contentResolver.openInputStream(
                    it
                )
            }
            val bitmap: Bitmap = BitmapFactory.decodeStream(input)
            binding.gambarItemUbah.setImageBitmap(bitmap)
            imageContent = getBytes(bitmap)
        }
    }
    fun getBytes(bitmap: Bitmap): ByteArray {
        val output: ByteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG,0,output)
        return output.toByteArray()
    }
}
