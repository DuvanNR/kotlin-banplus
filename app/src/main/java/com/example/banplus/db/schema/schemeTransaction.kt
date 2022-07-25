package com.example.banplus.db.schema

import androidx.lifecycle.LiveData
import androidx.room.*


@Entity(tableName = "transaction")
data class Transaction(
    @ColumnInfo(name = "tipo") val tipo: String,
    @ColumnInfo(name = "cedula") val cedula: String,
    @ColumnInfo(name = "telefono") val telefono: String,
    @ColumnInfo(name = "banco") val banco: String,
    @ColumnInfo(name = "monto") val monto: Double,
    @ColumnInfo(name = "nameBanco") val nameBanco: String,
    @ColumnInfo(name = "ref") val ref: String,
    @ColumnInfo(name = "hora") val hora: String,
    @ColumnInfo(name = "fecha") val fecha: String,
    @ColumnInfo(name = "message") val message: String,
    @PrimaryKey(autoGenerate = true) var id: Int = 0
)

@Entity(tableName = "transCount")
data class TransCount(
    @ColumnInfo(name = "amount")
    val amount: Double,
    @ColumnInfo(name = "total")
    val total: Int,
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
)

@Dao
interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(empresa: Transaction)

    @Query("SELECT * FROM `transaction` ORDER BY id DESC")
    fun getAll(): LiveData<List<Transaction>>

    @Query("SELECT (select sum(tr.monto) from `transaction` as tr) as amount, (1) as id,(select count(*) from `transaction` ) as total")
    fun countResult(): LiveData<TransCount>

}