package com.example.banplus.api

import com.example.banplus.Activity.ADD_TRANSACTIONS
import com.example.banplus.Activity.BANPLUS_URL
import com.example.banplus.Activity.LIST_TRANSACTIONS
import com.example.banplus.Activity.LOGIN
import com.example.banplus.api.ApiServiceInterceptor.NEEDS_AUTH_HEADER_KEY
import com.example.banplus.api.backend.dto.LoginDTO
import com.example.banplus.api.backend.response.InfoTerminalResponse
import com.example.banplus.api.backend.response.LoginResponse
import com.example.banplus.api.reportes.dto.ReportesDTO
import com.example.banplus.api.reportes.response.ReportesResponse
import com.example.banplus.api.vuelto.dto.Transactionp2pDTO
import com.example.banplus.api.vuelto.response.Tranferp2pResponse
import retrofit2.http.*

interface ApiServer {
    @POST(ADD_TRANSACTIONS)
    suspend fun emitTransaction(@Body data: Transactionp2pDTO): Tranferp2pResponse
    @POST(LIST_TRANSACTIONS)
    suspend fun ListTransation(@Body data: ReportesDTO):ReportesResponse
}

interface ApiServerBackend {
    @Headers("${NEEDS_AUTH_HEADER_KEY}: true")
    @GET("$BANPLUS_URL/{serial}")
    suspend fun getInfSerial(@Path("serial") serial: String):InfoTerminalResponse
    @POST(LOGIN)
    suspend fun login(@Body data: LoginDTO): LoginResponse
}
