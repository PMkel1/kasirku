package com.thoriq.kasirku.stock

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.thoriq.kasirku.R
import com.thoriq.kasirku.database.ListDatabase
import com.thoriq.kasirku.database.listbarang.DatabaseDao
import com.thoriq.kasirku.database.listbarang.ListBarang
import com.thoriq.kasirku.databinding.ActivityLoginBinding
import com.thoriq.kasirku.databinding.FragmentStockTambahBinding

class StockTambahFragment : Fragment() {

    lateinit var binding: FragmentStockTambahBinding
    lateinit var databaseDao: DatabaseDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStockTambahBinding.inflate(layoutInflater)
        val application = requireNotNull(this.activity).application
        databaseDao =  ListDatabase.getInstance((application)).ListDatabaseDao

        binding.buttonTambahItem.setOnClickListener {
            val nama = binding.namaItem.text.toString()
            val harga = binding.hargabarang.text.toString().toDouble()
            val tipe = binding.tipeBarang.text.toString()
            val barang = ListBarang(namaBarang = nama, harga = harga, tipeBarang = tipe)
            databaseDao.insert(barang)
            this.findNavController().navigate(R.id.action_stockTambahFragment_to_stockFragment)
        }
        return binding.root
    }

}