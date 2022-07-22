package com.example.banplus.db.dto

import androidx.lifecycle.LiveData
import androidx.room.*

@Entity(tableName = "empresa")
data class iEmpresa (
    @ColumnInfo(name = "razonSocial") val razonSocial: String,
    @ColumnInfo(name = "rif") val rif: String,
    @ColumnInfo(name = "telefono") val telefono: String,
    @PrimaryKey(autoGenerate = true) var id: Int = 0
)

@Dao
interface iEmpresaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(empresa: iEmpresa)
    @Query("SELECT * FROM empresa ORDER BY id DESC")
    fun getAll(): LiveData<List<iEmpresa>>
    @Delete
    fun delete(empresa:iEmpresa)
}