package com.thoriq.kasirku

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.thoriq.kasirku.databinding.FragmentDashboardBinding
import com.thoriq.kasirku.databinding.FragmentLoginBinding


class DashboardFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentDashboardBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_dashboard, container, false
        )
        binding.stock.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_dashboardFragment_to_stockFragment)
        }
//        binding.history.setOnClickListener {
//            view?.findNavController()?.navigate(R.id.action_dashboardFragment_to_stockFragment)
//        }
//        binding.kasir.setOnClickListener {
//            view?.findNavController()?.navigate(R.id.action_dashboardFragment_to_stockFragment)
//        }
        return binding.root
    }
}