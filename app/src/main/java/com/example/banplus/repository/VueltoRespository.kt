package com.example.banplus.repository

import com.example.banplus.api.ApiResponseStatus
import com.example.banplus.api.BanplusApi.retrofitService
import com.example.banplus.api.vuelto.dto.Transactionp2pDTO
import com.example.banplus.api.makeNetworkCall
import com.example.banplus.api.vuelto.VueltoDtoMapper
import com.example.banplus.api.vuelto.response.PagoDTO

class VueltoRespository {
    suspend fun emitTransaction(dataTransfer: Transactionp2pDTO): ApiResponseStatus<PagoDTO> = makeNetworkCall {
        val resp = retrofitService.emitTransaction(dataTransfer)
        val pagoDTO = resp.msRsB.pago
        val vueltoMapper =VueltoDtoMapper()
        vueltoMapper.converterPagoDTOaPago(pagoDTO)
    }
}
