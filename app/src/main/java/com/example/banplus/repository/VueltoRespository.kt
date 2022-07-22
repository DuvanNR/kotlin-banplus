package com.example.banplus.repository

import com.example.banplus._interface.iTransaction
import com.example.banplus.api.ApiResponseStatus
import com.example.banplus.api.ApiServer
import com.example.banplus.api.vuelto.dto.Transactionp2pDTO
import com.example.banplus.api.makeNetworkCall
import com.example.banplus.api.reportes.ReporteMapper
import com.example.banplus.api.reportes.response.ReportesResponse
import com.example.banplus.api.vuelto.VueltoDtoMapper
import com.example.banplus.api.vuelto.dto.MapperBodyVuelto
import com.example.banplus.api.vuelto.response.Tranferp2pResponse
import javax.inject.Inject

interface VueltoRespository {
    suspend fun emitTransaction(e:iTransaction): ApiResponseStatus<Tranferp2pResponse>
}
class VueltoRespositoryImp @Inject constructor(
    private val dataSource: ApiServer
): VueltoRespository {
    override suspend fun emitTransaction(e: iTransaction): ApiResponseStatus<Tranferp2pResponse> = makeNetworkCall {
        val mapperBody = MapperBodyVuelto()
        val body = mapperBody.converToResBodyaTransctionApi(e)
        val resp = dataSource.emitTransaction(body)

        val vueltoMapper =VueltoDtoMapper()
       vueltoMapper.converterPagoDTOaPago(resp)
    }


}


