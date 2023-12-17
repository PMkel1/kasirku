package com.thoriq.kasirku.stock

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.thoriq.kasirku.R
import com.thoriq.kasirku.cls.cekclass
import com.thoriq.kasirku.cls.dialogKasirku
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
                    if (databaseDao.getBarang(nama).isEmpty()){
                        val barang =
                            ListBarang(namaBarang = nama, harga = harga.toDouble(), tipeBarang = tipe)
                        databaseDao.insert(barang)
                        this.findNavController()
                            .navigate(R.id.action_stockTambahFragment_to_stockFragment)
                    }else{
                        dialog.dialogWarning("barang telah ada")
                    }
                }
            }
        }
        return binding.root
        }
    }