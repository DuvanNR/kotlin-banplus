package com.example.banplus.db.schema

import androidx.lifecycle.LiveData
import androidx.room.*

@Entity(tableName = "commerce")
data class Commerce (
    @ColumnInfo(name = "razonSocial") val razonSocial: String,
    @ColumnInfo(name = "rif") val rif: String,
    @ColumnInfo(name = "telefono") val telefono: String,
    @ColumnInfo(name = "tipo") val tipo: String,
    @PrimaryKey(autoGenerate = true) var id: Int = 0
)

@Dao
interface CommerceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(empresa: Commerce)
    @Query("SELECT * FROM commerce ORDER BY id DESC")
    fun getAll(): LiveData<List<Commerce>>
    @Query("SELECT * FROM commerce WHERE id = :id ORDER BY id DESC")
    fun getOneById(id: Int):LiveData<Commerce>
    @Delete
    fun delete(empresa:Commerce)
}