package com.thoriq.kasirku.register

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.thoriq.kasirku.database.ListDatabase
import com.thoriq.kasirku.database.akun.Akun
import com.thoriq.kasirku.database.akun.AkunDao

class RegisterActivityViewmodel(app: Application) : AndroidViewModel(app) {
    lateinit var akunDAO: AkunDao
    var allAkun: MutableLiveData<List<Akun>>
    init {
        akunDAO =  ListDatabase.getInstance((getApplication())).AkunDAO
        allAkun = MutableLiveData()
    }

    fun getAllAkunObservers(): MutableLiveData<List<Akun>> {
        return allAkun
    }

    @SuppressLint("NullSafeMutableLiveData")
    fun getAllAkun() {
        var list = akunDAO.getAllAkun()
        var akun = akunDAO.getByTugas("admin")
        list?.remove(akun)
        allAkun.postValue(list)
    }
    fun insertakun(akun: Akun){
        akunDAO.insert(akun)
        getAllAkun()
    }
    fun deleteAkunlistakun(akun: Akun){
        akunDAO.deleteAkun(akun.username)
        getAllAkun()
    }
    private var akun = MutableLiveData<Akun?>()
}
