package com.example.luckynum

import android.content.Context
import androidx.room.*

@Database(entities = [NumbersEntity::class], version = 1)
abstract class NumberDB:RoomDatabase() {
    abstract fun numberDao():NumberDAO

    companion object{
        @Volatile
        private var INSTANCE:NumberDB?=null

        fun getDatabase(context: Context):NumberDB{
            if(INSTANCE==null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        NumberDB::class.java,
                        "NumberDatabase"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE!!
        }
    }
}