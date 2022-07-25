package com.example.banplus.db.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.banplus.db.schema.*

@Database(entities = [Commerce::class, Transaction::class], version = 1)
abstract class DBdataSource:RoomDatabase() {
    abstract fun commerceDao():CommerceDao
    abstract fun transactionDao():TransactionDao
}
