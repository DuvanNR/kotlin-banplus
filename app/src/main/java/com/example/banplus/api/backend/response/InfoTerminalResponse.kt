package com.example.banplus.api.backend.response

import com.google.gson.annotations.SerializedName


data class InfoTerminalResponse (
    @SerializedName("name_commerce" ) var nameCommerce : String? = null,
    @SerializedName("num_cuenta"    ) var numCuenta    : String? = null,
    @SerializedName("cedula"        ) var cedula       : String? = null,
    @SerializedName("type_cedula"   ) var typeCedula   : String? = null,
    @SerializedName("telefono"      ) var telefono     : String? = null,
    @SerializedName("serial"        ) var serial       : String? = null,
    @SerializedName("rif"           ) var rif          : String? = null,
    @SerializedName("detail"   ) var detail   : String?             = null,
    @SerializedName("code"     ) var code     : String?             = null,
    @SerializedName("messages" ) var messages : ArrayList<Messages> = arrayListOf()
)


data class Messages (

    @SerializedName("token_class" ) var tokenClass : String? = null,
    @SerializedName("token_type"  ) var tokenType  : String? = null,
    @SerializedName("message"     ) var message    : String? = null

)