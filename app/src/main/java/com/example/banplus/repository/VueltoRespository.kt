package com.example.banplus.repository

import com.example.banplus.api.ApiResponseStatus
import com.example.banplus.api.BanplusApi.retrofitService
import com.example.banplus.api.vuelto.dto.Transactionp2pDTO
import com.example.banplus.api.makeNetworkCall
import com.example.banplus.api.vuelto.VueltoDtoMapper
import com.example.banplus.api.vuelto.dto.MapperBodyVuelto
import com.example.banplus.api.vuelto.response.Tranferp2pResponse

class VueltoRespository {
    suspend fun emitTransaction(tipo: String, cedula: String, telefono: String, banco: String, monto: String): ApiResponseStatus<Tranferp2pResponse> = makeNetworkCall {
        val mapperBody = MapperBodyVuelto()
        val body = mapperBody.converToResBodyaTransctionApi(tipo=tipo, cedula=cedula,telefono=telefono, banco=banco,monto=monto)
        val resp = retrofitService.emitTransaction(body)
        val pagoDTO = resp
        val vueltoMapper =VueltoDtoMapper()
        vueltoMapper.converterPagoDTOaPago(pagoDTO)
    }
}
