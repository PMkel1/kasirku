package com.thoriq.kasirku.database


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface DatabaseDao {

    @Insert
    fun insert(list: ListBarang)

    @Query("SELECT * FROM table_list_barang")
    fun getAllBarang(): List<ListBarang>?

    @Query("SELECT * FROM table_list_barang WHERE tipe_barang = :tipeBarang ")
    fun get(tipeBarang: String): List<ListBarang>

    @Query("DELETE FROM table_list_barang WHERE nama_barang = :namaBarang")
    fun clear(namaBarang: String)
}