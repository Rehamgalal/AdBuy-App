package com.example.adbuy.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Run::class],version = 1)
//@TypeConverters(Converters::class)
abstract class BuyDatabase : RoomDatabase(){
    abstract fun getDao():AdbuyDao
}