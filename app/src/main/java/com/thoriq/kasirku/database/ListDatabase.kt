package com.thoriq.kasirku.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.thoriq.kasirku.database.akun.Akun
import com.thoriq.kasirku.database.akun.AkunDao
import com.thoriq.kasirku.database.listbarang.DatabaseDao
import com.thoriq.kasirku.database.listbarang.ListBarang

@Database(entities = [ListBarang::class, Akun::class], version = 3, exportSchema = false)
abstract class ListDatabase : RoomDatabase() {

    abstract val ListDatabaseDao: DatabaseDao
    abstract val AkunDAO: AkunDao

    companion object {

        @Volatile
        private var INSTANCE: ListDatabase? = null

        fun getInstance(context: Context): ListDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ListDatabase::class.java,
                        "list_history_database"
                    )
                        .fallbackToDestructiveMigration().allowMainThreadQueries()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}