package com.thoriq.kasirku.kasir

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.thoriq.kasirku.database.listbarang.ListBarang
import com.thoriq.kasirku.database.ListDatabase

class KasirFragmentViewModel(app:Application) : AndroidViewModel(app) {
    var allBarang: MutableLiveData<List<ListBarang>>
    init {
        allBarang = MutableLiveData()
    }

    fun getAllBarangObservers():MutableLiveData<List<ListBarang>> {
        return allBarang
    }

    @SuppressLint("NullSafeMutableLiveData")
    fun getAllBarang() {
        val barangDao = ListDatabase.getInstance((getApplication())).ListDatabaseDao
        val list = barangDao.getAllBarang()

        allBarang.postValue(list)
    }
    private var barang = MutableLiveData<ListBarang?>()

    var hargaSementara = 0.0

    fun tambahHarga(barang: ListBarang){
        hargaSementara += barang.harga
        _harga.value = hargaSementara
    }
    private var _harga = MutableLiveData<Double?>()

    val harga: LiveData<Double?>
        get() = _harga

}
