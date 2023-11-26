package com.thoriq.kasirku.database.akun

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_akun")
data class Akun(
    @PrimaryKey(autoGenerate = true)
    var idAkun: Long= 0L,

    @ColumnInfo(name = "username")
    var username: String = "",

    @ColumnInfo(name = "password")
    var password: String = "",

    @ColumnInfo(name = "tugas")
    var tugas: String = "",
)