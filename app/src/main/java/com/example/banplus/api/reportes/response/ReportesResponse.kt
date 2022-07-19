package com.example.banplus.api.reportes.response

import com.google.gson.annotations.SerializedName


data class ReportesResponse (
    @SerializedName("msRsH" ) var msRsH : MsRsH? = MsRsH(),
    @SerializedName("msRsB" ) var msRsB : MsRsB
) {
    data class MsRsH (
        @SerializedName("codigo"           ) var codigo           : String? = null,
        @SerializedName("tipo"             ) var tipo             : Int?    = null,
        @SerializedName("descripcionError" ) var descripcionError : String? = null,
        @SerializedName("idioma"           ) var idioma           : Int?    = null
    )

    data class Movimiento (

        @SerializedName("numeroCuenta"   ) var numeroCuenta   : String? = null,
        @SerializedName("refeOper"       ) var refeOper       : String? = null,
        @SerializedName("horaProceso"    ) var horaProceso    : String? = null,
        @SerializedName("fechaProceso"   ) var fechaProceso   : String? = null,
        @SerializedName("fechaValor"     ) var fechaValor     : String? = null,
        @SerializedName("montoEfectivo"  ) var montoEfectivo  : String? = null,
        @SerializedName("monto"          ) var monto          : String? = null,
        @SerializedName("tipoMovimiento" ) var tipoMovimiento : String? = null,
        @SerializedName("montoTotal"     ) var montoTotal     : String? = null,
        @SerializedName("transaccion"    ) var transaccion    : String? = null,
        @SerializedName("conceptoMotivo" ) var conceptoMotivo : String? = null
    )
    data class Movimientos (
        @SerializedName("movimiento" ) var movimiento : List<Movimiento>

    )


    data class MsRsB (
        @SerializedName("movimientos" ) var movimientos : Movimientos

    )
}