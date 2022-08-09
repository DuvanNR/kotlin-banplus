package com.example.banplus.repository

import com.example.banplus.api.ApiResponseStatus
import com.example.banplus.api.ApiServerBackend
import com.example.banplus.api.backend.dto.LoginDTO
import com.example.banplus.api.backend.response.InfoTerminalResponse
import com.example.banplus.api.backend.response.LoginResponse
import com.example.banplus.api.makeNetworkCall
import javax.inject.Inject


interface BackendRespository {
    suspend fun login(loginDTO: LoginDTO):ApiResponseStatus<LoginResponse>
    suspend fun getInfoTerminal(serial: String):ApiResponseStatus<InfoTerminalResponse>
}
class BAckendRespositoryImp @Inject constructor(
    private val dataSource: ApiServerBackend

): BackendRespository {
    override suspend fun login(loginDTO: LoginDTO): ApiResponseStatus<LoginResponse> =  makeNetworkCall {
        dataSource.login(loginDTO)
    }
    override suspend fun getInfoTerminal(serial: String): ApiResponseStatus<InfoTerminalResponse> =  makeNetworkCall {
        println("serial________________________---------${serial}")
        dataSource.getInfSerial(serial)
    }

}
