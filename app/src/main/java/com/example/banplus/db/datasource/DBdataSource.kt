package com.example.banplus.db.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.banplus.db.schema.Commerce
import com.example.banplus.db.schema.CommerceDao

@Database(entities = [Commerce::class], version = 1)
abstract class DBdataSource:RoomDatabase() {
    abstract fun commerceDao():CommerceDao
}