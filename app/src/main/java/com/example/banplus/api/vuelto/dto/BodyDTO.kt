package com.example.banplus.api.vuelto.dto

class BodyDTO(
    val tipoDocumentoReceptor: String,
    val cedulaReceptor: String,
    val monto: Float,
    val bancoReceptor: String,
    val telefonoReceptor: String,
    val tipoDocumentoPagador: String,
    val cedulaPagador: String,
    val telefonoPagador: String,
    val checkSUM: String
)