package com.example.banplus.api.vuelto.response

import com.google.gson.annotations.SerializedName

class Tranferp2pResponse(
    @SerializedName("msRsH" ) var msRsH : MsRsH? = MsRsH(),
    @SerializedName("msRsB" ) var msRsB : MsRsB = MsRsB()
) {

    data class MsRsH (

        @SerializedName("codigo"           ) var codigo           : String? = null,
        @SerializedName("tipo"             ) var tipo             : Int?    = null,
        @SerializedName("descripcionError" ) var descripcionError : String? = null,
        @SerializedName("idioma"           ) var idioma           : Int?    = null

    )

    data class Pago (

        @SerializedName("fechaRespuesta"       ) var fechaRespuesta       : String? = null,
        @SerializedName("referenciaRespuesta"  ) var referenciaRespuesta  : String? = null,
        @SerializedName("codigoRespuesta"      ) var codigoRespuesta      : String? = null,
        @SerializedName("descripcionRespuesta" ) var descripcionRespuesta : String? = null

    )

    data class MsRsB (
        @SerializedName("pago" ) var pago : Pago? = Pago()

    )
}