package com.thoriq.kasirku.stock

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.thoriq.kasirku.R


class StockFragmentAdapter(private val dataSet: Array<String>) :
    RecyclerView.Adapter<StockFragmentAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nama: TextView
        val harga: TextView
        val jumlah: TextView
        init {
            // Define click listener for the ViewHolder's View.
            nama = view.findViewById(R.id.namaBarang)
            harga = view.findViewById(R.id.hargaBarang)
            jumlah = view.findViewById(R.id.jumlahBarang)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.row, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.nama.text = dataSet[position]
        viewHolder.harga.text = dataSet[position]
        viewHolder.jumlah.text = dataSet[position]
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}
