package com.thoriq.kasirku.database.listbarang

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Blob

@Entity(tableName = "table_list_barang")
data class ListBarang(
    @PrimaryKey(autoGenerate = true)
    var idBarang: Long = 0L,

    @ColumnInfo(name = "nama_barang")
    var namaBarang: String = "",

    @ColumnInfo(name = "tipe_barang")
    var tipeBarang: String = "",

    @ColumnInfo(name = "jumlah")
    var jumlah: Int = 0,

    @ColumnInfo(name = "harga")
    var harga: Double = 0.0,

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    var image: ByteArray? = null

)