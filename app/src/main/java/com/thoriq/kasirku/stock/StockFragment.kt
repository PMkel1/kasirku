package com.thoriq.kasirku.stock

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout.VERTICAL
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.thoriq.kasirku.R
import com.thoriq.kasirku.database.listbarang.DatabaseDao
import com.thoriq.kasirku.database.listbarang.ListBarang
import com.thoriq.kasirku.databinding.FragmentStockBinding


class StockFragment : Fragment(),StockFragmentAdapter.RowOnClickListener {
    lateinit var databaseDao: DatabaseDao
    lateinit var viewModel: StockFragmentViewModel
    lateinit var recyclerViewAdapter: StockFragmentAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentStockBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_stock, container, false
        )
        val application = requireNotNull(this.activity).application
        // Create an instance of the ViewModel Factory.
        binding.sleepList.apply {
            layoutManager = LinearLayoutManager(application)
            recyclerViewAdapter = StockFragmentAdapter(this@StockFragment)
            adapter = recyclerViewAdapter
            val divider = DividerItemDecoration(application,VERTICAL)
            addItemDecoration(divider)
        }
        viewModel =
            ViewModelProvider(
                this).get(StockFragmentViewModel::class.java)
        viewModel.getAllBarangObservers().observe(viewLifecycleOwner,Observer{
            recyclerViewAdapter.setListData(ArrayList(it))
            recyclerViewAdapter.notifyDataSetChanged()
        })
        viewModel.getAllBarang()
        binding.fab.setOnClickListener({
            this.findNavController().navigate(R.id.action_stockFragment_to_stockTambahFragment)
        })

        return binding.root
    }

    override fun onItemClickListener(barang: ListBarang) {
        val dialog = StockDialog(barang,viewModel)
        dialog.show(parentFragmentManager,"hehe")
    }
}
