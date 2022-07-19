package com.example.banplus.api

import com.example.banplus.ADD_TRANSACTIONS
import com.example.banplus.LIST_TRANSACTIONS
import com.example.banplus.URL_BASE
import com.example.banplus.api.reportes.dto.ReportesDTO
import com.example.banplus.api.reportes.response.ReportesResponse
import com.example.banplus.api.vuelto.dto.Transactionp2pDTO
import com.example.banplus.api.vuelto.response.Tranferp2pResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

private val retrofit = Retrofit.Builder()
    .baseUrl(URL_BASE)
    .addConverterFactory(GsonConverterFactory.create())
    .build()


interface ApiServer {
    @POST(ADD_TRANSACTIONS)
    suspend fun emitTransaction(@Body data: Transactionp2pDTO): Tranferp2pResponse
    @POST(LIST_TRANSACTIONS)
    suspend fun ListTransation(@Body data: ReportesDTO):ReportesResponse
}
object BanplusApi {
    val retrofitService: ApiServer by lazy {
        retrofit.create(ApiServer::class.java)
    }
}