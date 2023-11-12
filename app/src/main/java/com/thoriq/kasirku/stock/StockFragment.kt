package com.thoriq.kasirku.stock

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.thoriq.kasirku.R
import com.thoriq.kasirku.databinding.FragmentDashboardBinding
import com.thoriq.kasirku.databinding.FragmentStockBinding

/**
 * A simple [Fragment] subclass.
 * Use the [StockFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StockFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentStockBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_stock, container, false
        )

        return binding.root
    }
}