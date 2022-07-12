package com.example.banplus.api.response

class TranferBody(
    val pago: Pago
)

class Pago(
    val fechaRespuesta: String,
    val referenciaRespuesta: String,
    val codigoRespuesta: String,
    val descripcionRespuesta: String

)