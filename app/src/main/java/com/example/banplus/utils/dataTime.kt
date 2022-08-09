package com.example.banplus.utils

import java.text.SimpleDateFormat
import java.util.*

class iFechahora(
   val hora: String = "",
   val fecha: String = ""
)

fun getDatetime(): iFechahora {
    val formatter = SimpleDateFormat("hh:mm:ss a")
    val formatterfecha = SimpleDateFormat("dd/M/yyyy")
   return iFechahora(
        hora = formatter.format(Date()),
        fecha = formatterfecha.format(Date())
    )
}
fun getDay(): String {
    val formatter = SimpleDateFormat("dd")
    return  formatter.format(Date())
}