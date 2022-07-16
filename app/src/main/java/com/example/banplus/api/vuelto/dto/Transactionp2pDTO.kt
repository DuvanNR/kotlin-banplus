package com.example.banplus.api.vuelto.dto

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class Transactionp2pDTO(
    @SerializedName("msRqH" ) var msRqH : MsRqH? = MsRqH(),
    @SerializedName("msRqB" ) var msRqB : MsRqB? = MsRqB()

){

    data class MsRqH (

        @SerializedName("idServicio"            ) var idServicio            : Int?    = null,
        @SerializedName("ipUsuario"             ) var ipUsuario             : String? = null,
        @SerializedName("idFuenteAutenticacion" ) var idFuenteAutenticacion : Int?    = null,
        @SerializedName("ipServer"              ) var ipServer              : String? = null,
        @SerializedName("ipMidServer"           ) var ipMidServer           : String? = null,
        @SerializedName("codigoAplicacion"      ) var codigoAplicacion      : Int?    = null,
        @SerializedName("idioma"                ) var idioma                : Int?    = null

    )

    data class MsRqB (

        @SerializedName("tipoDocumentoReceptor" ) var tipoDocumentoReceptor : String? = null,
        @SerializedName("cedulaReceptor"        ) var cedulaReceptor        : String? = null,
        @SerializedName("monto"                 ) var monto                 : String? = null,
        @SerializedName("bancoReceptor"         ) var bancoReceptor         : String? = null,
        @SerializedName("telefonoReceptor"      ) var telefonoReceptor      : String? = null,
        @SerializedName("tipoDocumentoPagador"  ) var tipoDocumentoPagador  : String? = null,
        @SerializedName("cedulaPagador"         ) var cedulaPagador         : String? = null,
        @SerializedName("telefonoPagador"       ) var telefonoPagador       : String? = null,
        @SerializedName("checkSUM"              ) var checkSUM              : String? = null

    )
}