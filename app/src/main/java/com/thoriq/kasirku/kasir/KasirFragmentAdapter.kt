package com.thoriq.kasirku.kasir



import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.thoriq.kasirku.R
import com.thoriq.kasirku.database.listbarang.ListBarang


class KasirFragmentAdapter(val listener: RowOnClickListener): RecyclerView.Adapter<KasirFragmentAdapter.ViewHolder>() {

    var barangs = ArrayList<ListBarang>()

    fun setListData(data: ArrayList<ListBarang>){
        this.barangs = data
    }

    class ViewHolder(view: View,listener: RowOnClickListener) : RecyclerView.ViewHolder(view) {
        val nama: TextView
        val gambar: ImageView
        init {
            // Define click listener for the ViewHolder's View.
            nama = view.findViewById(com.thoriq.kasirku.R.id.namaItem)
            gambar = view.findViewById(com.thoriq.kasirku.R.id.gambarItem)
        }
        fun bind(data: ListBarang){
            val bmp = data.image?.let { BitmapFactory.decodeByteArray(data.image, 0, it.size) }
            gambar.setImageBitmap(bmp)
            nama.text=data.namaBarang
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(com.thoriq.kasirku.R.layout.kasir_item, viewGroup, false)

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



