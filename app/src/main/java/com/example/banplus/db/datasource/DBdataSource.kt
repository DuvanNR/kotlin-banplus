package com.example.banplus.db.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.banplus.db.dto.iEmpresa
import com.example.banplus.db.dto.iEmpresaDao

@Database(entities = [iEmpresa::class], version = 1)
abstract class DBdataSource:RoomDatabase() {
    abstract fun empresaDao(): iEmpresaDao

}