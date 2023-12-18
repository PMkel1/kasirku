package com.thoriq.kasirku.register

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.thoriq.kasirku.R
import com.thoriq.kasirku.database.akun.Akun
import com.thoriq.kasirku.stock.StockFragmentAdapter
import com.thoriq.kasirku.stock.toCurrencyFormat

class RegisterActivityAdapter(val listener: RowOnClickListener): RecyclerView.Adapter<RegisterActivityAdapter.ViewHolder>() {

    var barangs = ArrayList<Akun>()
    fun setListData(data: ArrayList<Akun>){
        this.barangs = data
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nama: TextView
        val tugas: TextView
        val hapus: Button
        init {
            // Define click listener for the ViewHolder's View.
            nama = view.findViewById(R.id.username)
            tugas = view.findViewById(R.id.password)
            hapus = view.findViewById(R.id.hapus)

        }
        fun bind(data: Akun,listener: RowOnClickListener){
            nama.text= data.username
            tugas.text = data.tugas
            hapus.setOnClickListener{listener.onDeleteUserClickListener(data)}
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.akun_row, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.setOnClickListener{
            listener.onItemClickListener(barangs[position])
        }
        viewHolder.bind(barangs[position],listener)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = barangs.size

    interface RowOnClickListener{
        fun onDeleteUserClickListener(akun: Akun)
        fun onItemClickListener(akun: Akun)
    }
}