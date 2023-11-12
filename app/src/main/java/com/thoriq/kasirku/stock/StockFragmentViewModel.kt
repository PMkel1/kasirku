package com.thoriq.kasirku.stock

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thoriq.kasirku.database.DatabaseDao
import com.thoriq.kasirku.database.ListBarang
import com.thoriq.kasirku.database.ListDatabase

class StockFragmentViewModel(app:Application) : AndroidViewModel(app) {
    lateinit var allBarang: MutableLiveData<List<ListBarang>>
    init {
        allBarang = MutableLiveData()
    }

    fun getAllBarangObservers():MutableLiveData<List<ListBarang>> {
        return allBarang
    }

    @SuppressLint("NullSafeMutableLiveData")
    fun getAllBarang() {
        val barangDao = ListDatabase.getInstance((getApplication()))?.ListDatabaseDao
        val list = barangDao?.getAllBarang()

        allBarang.postValue(list)
    }
    fun insertBarang(listBarang: ListBarang){
        val barangDao = ListDatabase.getInstance((getApplication()))?.ListDatabaseDao
        barangDao?.insert(listBarang)
        getAllBarang()
    }
    private var barang = MutableLiveData<ListBarang?>()


}
