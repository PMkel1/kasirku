package com.thoriq.kasirku.stock

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.thoriq.kasirku.R
import com.thoriq.kasirku.database.listbarang.ListBarang

class StockDialog(var barang: ListBarang, var delete: StockFragmentViewModel) : DialogFragment() {

    var action = StockFragmentDirections.actionStockFragmentToStockUbahFragment(barang)
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle(R.string.pilih)
                .setItems(
                    R.array.pilihan,
                    DialogInterface.OnClickListener { dialog, which ->
                        // The 'which' argument contains the index position
                        // of the selected item
                        when (which){
                            0 -> this.findNavController().navigate(R.id.action_stockFragment_to_detailStockFragment)
                            1 -> this.findNavController().navigate(action)
                            2 -> delete.deleteBarang(barang)
                        }

                    })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}