package com.example.banplus.api.backend.response

import com.google.gson.annotations.SerializedName


data class InfoTerminalResponse(
    @SerializedName("name_commerce") var nameCommerce: String = "",
    @SerializedName("num_cuenta") var numCuenta: String = "",
    @SerializedName("cedula") var cedula: String = "",
    @SerializedName("type_cedula") var typeCedula: String = "",
    @SerializedName("telefono") var telefono: String = "",
    @SerializedName("password") var password: String = "",
    @SerializedName("serial") var serial: String? = null,
    @SerializedName("rif") var rif: String? = null,
    @SerializedName("detail") var detail: String? = null,
    @SerializedName("code") var code: String? = null,
    @SerializedName("messages") var messages: ArrayList<Messages> = arrayListOf()
)


data class Messages(

    @SerializedName("token_class") var tokenClass: String? = null,
    @SerializedName("token_type") var tokenType: String? = null,
    @SerializedName("message") var message: String? = null

)