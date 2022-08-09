package com.example.banplus.api.backend.response

import com.google.gson.annotations.SerializedName


data class InfoTerminalResponse (

    @SerializedName("name_commerce" ) var nameCommerce : String? = null,
    @SerializedName("num_cuenta"    ) var numCuenta    : String? = null,
    @SerializedName("cedula"        ) var cedula       : String? = null,
    @SerializedName("type_cedula"   ) var typeCedula   : String? = null,
    @SerializedName("telefono"      ) var telefono     : String? = null,
    @SerializedName("serial"        ) var serial       : String? = null,
    @SerializedName("rif"           ) var rif          : String? = null

)