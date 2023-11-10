package com.thoriq.kasirku.database

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "table_list_barang")
data class ListBarang(
    @ColumnInfo(name = "nama_barang")
    var namaBarang: String = "",

    @ColumnInfo(name = "tipe_barang")
    var tipeBarang: String = "",

    @ColumnInfo(name = "jumlah")
    var jumlah: Int = 0,

    @ColumnInfo(name = "harga")
    var harga: Double = 0.0
)