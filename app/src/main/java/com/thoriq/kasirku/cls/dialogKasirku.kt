package com.thoriq.kasirku.cls

import android.content.Context
import androidx.appcompat.app.AlertDialog

class dialogKasirku(val context: Context) {
    fun dialogWarning(kata: String){
        AlertDialog.Builder(context).setTitle("warning").setMessage(kata).setNegativeButton("ok",null).show()
    }
}