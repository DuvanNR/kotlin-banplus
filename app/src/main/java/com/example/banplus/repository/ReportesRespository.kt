package com.example.banplus.repository

import com.example.banplus.api.ApiResponseStatus
import com.example.banplus.api.BanplusApi.retrofitService
import com.example.banplus.api.makeNetworkCall
import com.example.banplus.api.reportes.ReporteMapper
import com.example.banplus.api.reportes.response.ReportesResponse

class ReportesRespository {
    suspend fun GetReportes(): ApiResponseStatus<List<ReportesResponse.Movimiento>> = makeNetworkCall {
        val mapperBody = ReporteMapper()
        val body = mapperBody.converToResponse()
        val resp = retrofitService.ListTransation(body)
        resp.msRsB.movimientos.movimiento
    }
}
