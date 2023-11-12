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
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thoriq.kasirku.R
import com.thoriq.kasirku.database.ListBarang
import com.thoriq.kasirku.database.ListDatabase
import com.thoriq.kasirku.databinding.FragmentStockBinding

/**
 * A simple [Fragment] subclass.
 * Use the [StockFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StockFragment : Fragment(),StockFragmentAdapter.RowOnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    lateinit var viewModel: StockFragmentViewModel
    lateinit var recyclerViewAdapter: StockFragmentAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
            viewModel.getAllBarangObservers().observe(this,Observer{
            recyclerViewAdapter.setListData(ArrayList(it))
            recyclerViewAdapter.notifyDataSetChanged()
        })
        return binding.root
    }

    override fun onDeleteUserClickListener(barang: ListBarang) {
        TODO("Not yet implemented")
    }

    override fun onItemClickListener(barang: ListBarang) {
        TODO("Not yet implemented")
    }
}