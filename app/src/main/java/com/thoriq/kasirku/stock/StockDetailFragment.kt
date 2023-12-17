package com.thoriq.kasirku.stock

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.thoriq.kasirku.R
import com.thoriq.kasirku.cls.dialogKasirku
import com.thoriq.kasirku.database.ListDatabase
import com.thoriq.kasirku.database.listbarang.DatabaseDao
import com.thoriq.kasirku.databinding.FragmentDetailStockBinding
import com.thoriq.kasirku.databinding.FragmentStokUbahBinding


class StockDetailFragment : Fragment() {

    lateinit var binding: FragmentDetailStockBinding
    lateinit var databaseDao: DatabaseDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailStockBinding.inflate(inflater, container, false)
        val application = requireNotNull(this.activity).application
        databaseDao = ListDatabase.getInstance(application).ListDatabaseDao
        val dialog = dialogKasirku(requireContext())
        var idBarang = requireArguments().getLong("idBarang")
        var listBarang = databaseDao.getById(idBarang)
        // Menampilkan data barang pada UI
        binding.namaItem.setText(listBarang.namaBarang)
        binding.hargabarang.setText(listBarang.harga.toString())
        binding.tipeBarang.setText(listBarang.tipeBarang)

        return binding.root
    }

}