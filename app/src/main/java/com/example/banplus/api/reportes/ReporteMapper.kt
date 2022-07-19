package com.example.banplus.api.reportes

import com.example.banplus.api.reportes.dto.ReportesDTO

class ReporteMapper {

    private fun getMsRqH():ReportesDTO.MsRqH {
        return ReportesDTO.MsRqH(
            ipUsuario = "123.123.121.121",
            idFuenteAutenticacion = "11",
            documentoMaestro = "",
            documentoDelegado = "",
            idUsuario = "1",
            idPerfil = "1",
            idRol = "1",
            idCanal = "1",
            idOpcionMenu = "1",
            idComponente = "14",
            idMetodo = "15",
            idServicio = "1",
            ipServer = "123.123.121.121",
            ipMidServer = "123.123.121.121",
            idAplicacion = "18",
            codigoProcesamiento = "19",
            idioma = "1"
        )
    }

    private fun getMsRqB(): ReportesDTO.MsRqB{
        return ReportesDTO.MsRqB(
            tipoDocumento = "V",
            numeroDocumento = "17142863",
            tipoMovimiento = "0",
            numeroCuenta = "01740131991314234475",
            fechaHoraDesde = "2022-07-11 00:00:01",
            fechaHoraHasta = "2022-07-13 23:59:01",
            montoDesde = "0",
            montoHasta = "10000",
            pagina = "1",
            paginado = "10"
        )
    }

    fun converToResponse(): ReportesDTO {
        return ReportesDTO(
            msRqB = getMsRqB(),
            msRqH = getMsRqH()
        )
    }
}