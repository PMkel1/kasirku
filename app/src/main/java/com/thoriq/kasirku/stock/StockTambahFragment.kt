package com.thoriq.kasirku.stock

import android.companion.CompanionDeviceManager.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.thoriq.kasirku.R
import com.thoriq.kasirku.cls.cekclass
import com.thoriq.kasirku.cls.dialogKasirku
import com.thoriq.kasirku.database.ListDatabase
import com.thoriq.kasirku.database.listbarang.DatabaseDao
import com.thoriq.kasirku.database.listbarang.ListBarang
import com.thoriq.kasirku.databinding.FragmentStockTambahBinding
import java.io.ByteArrayOutputStream
import java.io.InputStream


class StockTambahFragment : Fragment() {

    lateinit var binding: FragmentStockTambahBinding
    lateinit var databaseDao: DatabaseDao
    private val pickImage = 100
    private var imageUri: Uri? = null
    private var imageContent: ByteArray? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStockTambahBinding.inflate(layoutInflater)
        val application = requireNotNull(this.activity).application
        databaseDao = ListDatabase.getInstance((application)).ListDatabaseDao
        val dialog = dialogKasirku(requireContext())
        val cc = cekclass()
        binding.buttonTambahItem.setOnClickListener {
            val nama = binding.namaItem.text.toString()
            val harga = binding.hargabarang.text.toString()
            val tipe = binding.tipeBarang.text.toString()
            if (nama.isEmpty() or harga.isEmpty() or tipe.isEmpty()) {
                dialog.dialogWarning("nama, harga, tipe tidak boleh kosong")
            } else {
                if (cc.mengandungSimbol(nama) or cc.mengandungSimbol(tipe) or cc.mengandungSimbol(harga)){
                    dialog.dialogWarning("nama, harga, tipe tidak boleh mengandung simbol")
                } else if (harga.length < 3) {
                    dialog.dialogWarning("harga minimal 3 digit ")
                } else if (harga.first().equals('0')) {
                    dialog.dialogWarning("harga tidak boleh dimulai dengan 0")
                } else {
                    val barang = databaseDao.getBarang(nama)
                    if (barang == null){
                        val barang = ListBarang(namaBarang = nama, harga = harga.toDouble(), tipeBarang = tipe, image =imageContent)
                        databaseDao.insert(barang)
                        this.findNavController()
                            .navigate(R.id.action_stockTambahFragment_to_stockFragment)
                    }else{
                        dialog.dialogWarning("barang telah ada")
                    }
                }
            }
        }

        binding.gambarItem.setOnClickListener{
            val gallery = Intent(Intent.ACTION_GET_CONTENT)
            gallery.setType("image/*")
            startActivityForResult(gallery, pickImage)
        }

        return binding.root
        }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data
            val input: InputStream? = imageUri?.let {
                requireContext().contentResolver.openInputStream(
                    it
                )
            }
            val bitmap: Bitmap =BitmapFactory.decodeStream(input)
            binding.gambarItem.setImageBitmap(bitmap)
            imageContent = getBytes(bitmap)
        }
    }
    fun getBytes(bitmap: Bitmap): ByteArray {
        val output:ByteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG,0,output)
        return output.toByteArray()
    }
}
