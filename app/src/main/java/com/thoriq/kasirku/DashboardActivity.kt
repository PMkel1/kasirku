package com.thoriq.kasirku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.thoriq.kasirku.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {
    lateinit var binding: ActivityDashboardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.kasir.setOnClickListener({
            startActivity(Intent(this, KasirActivity::class.java))
        })
        binding.stock.setOnClickListener({
            startActivity(Intent(this, StockActivity::class.java))
        })
    }
}