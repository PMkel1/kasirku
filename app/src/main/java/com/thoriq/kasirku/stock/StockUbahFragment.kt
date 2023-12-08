package com.thoriq.kasirku.stock

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.thoriq.kasirku.R
import com.thoriq.kasirku.database.ListDatabase
import com.thoriq.kasirku.database.listbarang.DatabaseDao
import com.thoriq.kasirku.database.listbarang.ListBarang
import com.thoriq.kasirku.databinding.FragmentStokUbahBinding

class StockUbahFragment : Fragment() {

    lateinit var binding: FragmentStokUbahBinding
    lateinit var databaseDao: DatabaseDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStokUbahBinding.inflate(inflater, container, false)
        val application = requireNotNull(this.activity).application
        databaseDao = ListDatabase.getInstance(application).ListDatabaseDao

        var idBarang = requireArguments().getLong("idBarang")
        var listBarang = databaseDao.getById(idBarang)
        // Menampilkan data barang pada UI
        binding.namaItem.setText(listBarang.namaBarang)
        binding.hargabarang.setText(listBarang.harga.toString())
        binding.tipeBarang.setText(listBarang.tipeBarang)

        binding.buttonUbahItem.setOnClickListener {
            // Retrieve values from UI elements
            val nama = binding.namaItem.text.toString()
            val harga = binding.hargabarang.text.toString().toDouble()
            val tipe = binding.tipeBarang.text.toString()

            val barang = ListBarang(idBarang = listBarang.idBarang, namaBarang = nama, harga = harga, tipeBarang = tipe)
            databaseDao.update(barang)
            this.findNavController().navigate(R.id.action_stockUbahFragment_to_stockFragment)
        }

        binding.buttonBatal.setOnClickListener {
            this.findNavController().navigate(R.id.action_stockUbahFragment_to_stockFragment)
        }

        return binding.root
    }
}
