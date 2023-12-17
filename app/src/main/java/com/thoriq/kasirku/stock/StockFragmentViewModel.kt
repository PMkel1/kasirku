package com.thoriq.kasirku.stock

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.thoriq.kasirku.database.listbarang.ListBarang
import com.thoriq.kasirku.database.ListDatabase
import com.thoriq.kasirku.database.listbarang.DatabaseDao

class StockFragmentViewModel(app:Application) : AndroidViewModel(app) {
    lateinit var databaseDao: DatabaseDao
    var allBarang: MutableLiveData<List<ListBarang>>
    init {
        databaseDao =  ListDatabase.getInstance((getApplication())).ListDatabaseDao
        allBarang = MutableLiveData()
    }

    fun getAllBarangObservers():MutableLiveData<List<ListBarang>> {
        return allBarang
    }

    @SuppressLint("NullSafeMutableLiveData")
    fun getAllBarang() {
        val list = databaseDao.getAllBarang()

        allBarang.postValue(list)
    }
    fun insertBarang(listBarang: ListBarang){
        databaseDao.insert(listBarang)
        getAllBarang()
    }
    fun deleteBarang(listBarang: ListBarang){
        databaseDao.clear(listBarang.namaBarang)
        getAllBarang()
    }
    private var barang = MutableLiveData<ListBarang?>()
    
}
