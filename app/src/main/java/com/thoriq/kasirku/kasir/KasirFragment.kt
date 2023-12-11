package com.thoriq.kasirku.kasir

import android.icu.text.NumberFormat
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout.VERTICAL
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.thoriq.kasirku.R
import com.thoriq.kasirku.database.listbarang.ListBarang
import com.thoriq.kasirku.databinding.FragmentKasirBinding
import com.thoriq.kasirku.databinding.FragmentStockBinding
import java.util.Locale


class KasirFragment : Fragment(),KasirFragmentAdapter.RowOnClickListener {
    lateinit var binding: FragmentKasirBinding
    lateinit var viewModel: KasirFragmentViewModel
    lateinit var recyclerViewAdapter: KasirFragmentAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentKasirBinding.inflate(layoutInflater)
        val application = requireNotNull(this.activity).application

        val manager = GridLayoutManager(application, 2)
        manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int) =  when (position) {
                0->1
                else -> 1
            }
        }

        // Create an instance of the ViewModel Factory.
        binding.kasirList.apply {
            layoutManager = manager
            recyclerViewAdapter = KasirFragmentAdapter(this@KasirFragment)
            adapter = recyclerViewAdapter
            val divider = DividerItemDecoration(application,VERTICAL)
            addItemDecoration(divider)
        }
        viewModel =
            ViewModelProvider(
                this).get(KasirFragmentViewModel::class.java)
        viewModel.getAllBarangObservers().observe(viewLifecycleOwner,Observer{
            recyclerViewAdapter.setListData(ArrayList(it))
            recyclerViewAdapter.notifyDataSetChanged()
        })
        viewModel.getAllBarang()
        viewModel.harga.observe(viewLifecycleOwner,{
            binding.harga.text =  toCurrencyFormat(it!!)
        })
        return binding.root
    }

    override fun onItemClickListener(barang: ListBarang) {
        viewModel.tambahHarga(barang)
        Toast.makeText(activity,viewModel.hargaSementara.toString(),Toast.LENGTH_SHORT).show()
    }

    fun toCurrencyFormat(harga:Double): String {
        val localeID = Locale("in", "ID")
        val numberFormat = NumberFormat.getCurrencyInstance(localeID)
        numberFormat.minimumFractionDigits = 0
        return numberFormat.format(harga)
    }
}
