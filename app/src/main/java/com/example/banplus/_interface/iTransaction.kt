package com.example.banplus._interface

data class iTransaction(
    val tipo: String = "",
    val cedula: String = "",
    val telefono: String = "",
    val banco: String = "",
    val monto: String = "",
    val nameBanco: String = "",
    val ref: String = "",
    val hora: String = "",
    val fecha: String = "",
    val message: String = "",
)