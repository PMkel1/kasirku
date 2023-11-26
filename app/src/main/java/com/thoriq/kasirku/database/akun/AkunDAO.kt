package com.thoriq.kasirku.database.akun

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.thoriq.kasirku.database.listbarang.ListBarang

@Dao
interface AkunDao {

    @Insert
    fun insert(akun:Akun)

    @Query("SELECT * FROM table_akun")
    fun getAllAkun(): List<Akun>?

    @Query("SELECT * FROM table_akun WHERE username = :nama ")
    fun getByUsername(nama: String): Akun?

    @Query("DELETE FROM table_akun WHERE username = :namaAkun")
    fun deleteAkun(namaAkun: String)
}