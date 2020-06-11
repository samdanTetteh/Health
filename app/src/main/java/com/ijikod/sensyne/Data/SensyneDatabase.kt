package com.ijikod.sensyne.Model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ijikod.Data.Dao


@Database(entities = [Hospital::class], version = 1, exportSchema = false)
abstract class SensyneDatabase: RoomDatabase() {

    abstract fun hospitalDao(): Dao


    companion object{
        @Volatile
        private var INSTANCE: SensyneDatabase? = null

        fun getDatabase(context: Context): SensyneDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        SensyneDatabase::class.java,
                        "sensyne.db"
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }
}