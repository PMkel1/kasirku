package com.thoriq.kasirku.stock

import android.icu.text.NumberFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.thoriq.kasirku.R
import com.thoriq.kasirku.database.listbarang.ListBarang
import com.thoriq.kasirku.register.RegisterActivity
import java.util.Locale


class StockFragmentAdapter(val listener: StockFragment): RecyclerView.Adapter<StockFragmentAdapter.ViewHolder>() {

    var barangs = ArrayList<ListBarang>()

    fun setListData(data: ArrayList<ListBarang>){
        this.barangs = data
    }

    class ViewHolder(view: View,listener: RowOnClickListener) : RecyclerView.ViewHolder(view) {
        val nama: TextView
        val harga: TextView
        val jumlah: TextView
        val tipe: TextView
        init {
            // Define click listener for the ViewHolder's View.
            nama = view.findViewById(R.id.namaBarang)
            harga = view.findViewById(R.id.hargaBarang)
            jumlah = view.findViewById(R.id.jumlahBarang)
            tipe = view.findViewById(R.id.tipeBarang)
        }
        fun bind(data: ListBarang){
            nama.text=data.namaBarang
            harga.text = toCurrencyFormat(data.harga)
            jumlah.text = data.jumlah.toString()
            tipe.text = data.tipeBarang
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.row, viewGroup, false)

        return ViewHolder(view,listener)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.setOnClickListener{
            listener.onItemClickListener(barangs[position])
        }
        viewHolder.bind(barangs[position])
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = barangs.size

    interface RowOnClickListener{
        fun onItemClickListener(barang: ListBarang)
    }
}
fun toCurrencyFormat(harga:Double): String {
    val localeID = Locale("in", "ID")
    val numberFormat = NumberFormat.getCurrencyInstance(localeID)
    numberFormat.minimumFractionDigits = 0
    return numberFormat.format(harga)
}
