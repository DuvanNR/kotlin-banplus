package com.example.banplus.repository

import com.example.banplus.api.ApiResponseStatus
import com.example.banplus.api.ApiServer
import com.example.banplus.api.makeNetworkCall
import com.example.banplus.api.reportes.ReporteMapper

import com.example.banplus.api.reportes.response.ReportesResponse
import javax.inject.Inject


interface ReportesRespository {
    suspend fun GetReportes():ApiResponseStatus<List<ReportesResponse.Movimiento>>
}
class ReportesRespositoryImp @Inject constructor(
    private val dataSource: ApiServer
): ReportesRespository {
    override suspend fun GetReportes(): ApiResponseStatus<List<ReportesResponse.Movimiento>> =  makeNetworkCall {
        val mapperBody = ReporteMapper()
        val body = mapperBody.converToResponse()
        val resp = dataSource.ListTransation(body)
        resp.msRsB.movimientos.movimiento
    }
}
