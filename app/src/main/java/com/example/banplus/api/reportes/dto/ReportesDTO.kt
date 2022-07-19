package com.example.banplus.api.reportes.dto

import com.google.gson.annotations.SerializedName

data class ReportesDTO(
    @SerializedName("msRqH") var msRqH: MsRqH? = MsRqH(),
    @SerializedName("msRqB") var msRqB: MsRqB? = MsRqB()

) {
    data class MsRqH(
        @SerializedName("ipUsuario") var ipUsuario: String? = null,
        @SerializedName("idFuenteAutenticacion") var idFuenteAutenticacion: String? = null,
        @SerializedName("documentoMaestro") var documentoMaestro: String? = null,
        @SerializedName("documentoDelegado") var documentoDelegado: String? = null,
        @SerializedName("idUsuario") var idUsuario: String? = null,
        @SerializedName("idPerfil") var idPerfil: String? = null,
        @SerializedName("idRol") var idRol: String? = null,
        @SerializedName("idCanal") var idCanal: String? = null,
        @SerializedName("idOpcionMenu") var idOpcionMenu: String? = null,
        @SerializedName("idComponente") var idComponente: String? = null,
        @SerializedName("idMetodo") var idMetodo: String? = null,
        @SerializedName("idServicio") var idServicio: String? = null,
        @SerializedName("ipServer") var ipServer: String? = null,
        @SerializedName("ipMidServer") var ipMidServer: String? = null,
        @SerializedName("idAplicacion") var idAplicacion: String? = null,
        @SerializedName("codigoProcesamiento") var codigoProcesamiento: String? = null,
        @SerializedName("idioma") var idioma: String? = null

    )

    data class MsRqB(
        @SerializedName("tipoDocumento") var tipoDocumento: String? = null,
        @SerializedName("numeroDocumento") var numeroDocumento: String? = null,
        @SerializedName("tipoMovimiento") var tipoMovimiento: String? = null,
        @SerializedName("numeroCuenta") var numeroCuenta: String? = null,
        @SerializedName("fechaHoraDesde") var fechaHoraDesde: String? = null,
        @SerializedName("fechaHoraHasta") var fechaHoraHasta: String? = null,
        @SerializedName("montoDesde") var montoDesde: String? = null,
        @SerializedName("montoHasta") var montoHasta: String? = null,
        @SerializedName("pagina") var pagina: String? = null,
        @SerializedName("paginado") var paginado: String? = null
    )

}