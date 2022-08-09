package com.example.banplus.utils

import com.example.banplus._interface.iTransaction
import com.example.banplus.db.schema.Commerce
import java.math.BigInteger
import java.security.MessageDigest

fun getChecksum(re: iTransaction, pag: Commerce): String {
    val aa = """
        <tipoDocumentoReceptor>${re.tipo}</tipoDocumentoReceptor>
        <cedulaReceptor>${re.cedula}</cedulaReceptor>
        <monto>${re.monto}</monto>
        <bancoReceptor>${re.banco}</bancoReceptor>
        <telefonoReceptor>${re.telefono}</telefonoReceptor>
        <tipoDocumentoPagador>${pag.tipo}</tipoDocumentoPagador>
        <cedulaPagador>${pag.rif}</cedulaPagador>
        <telefonoPagador>${pag.telefono}</telefonoPagador>
         """
    val sha = getSHA512(aa)
    print("_________________________${sha}_____________________________")
    return sha
}
fun getSHA512(input:String):String{
    val md: MessageDigest = MessageDigest.getInstance("SHA-512")
    val messageDigest = md.digest(input.toByteArray())
    val no = BigInteger(1, messageDigest)
    var hashtext: String = no.toString(16)
    while (hashtext.length < 128) {
        hashtext = "0$hashtext"
    }
    return hashtext
}
//686375BA11F93A3D99376DD5B8F2C1F8B402B0AA14278D03211ABA4A847925E928469DEE7CFB8D5D634DC6F28F614D15A70D1B67C29177450DE73958E42F0B1E
//facd613abb952b48dc60f59aa0d504c7465e3b1af6a459d05735c4b0d08832a55391cb7e0756948bcfc4c4c3116c70d0e9ebb3da7b76c2d80566e2bd4552e570