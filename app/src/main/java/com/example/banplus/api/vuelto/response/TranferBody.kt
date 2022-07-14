package com.example.banplus.api.vuelto.response

class TranferBodyDTO(
    val pago: PagoDTO
)

class PagoDTO(
    val fechaRespuesta: String,
    val referenciaRespuesta: String,
    val codigoRespuesta: String,
    val descripcionRespuesta: String

)