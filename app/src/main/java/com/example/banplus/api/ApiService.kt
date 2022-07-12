package com.example.banplus.api

import com.example.banplus.ADD_TRANSACTIONS
import com.example.banplus.URL_BASE
import com.example.banplus.api.dto.Transactionp2pDTO
import com.example.banplus.api.response.Tranferp2pResponse
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

private val retrofit = Retrofit.Builder()
    .baseUrl(URL_BASE)
    .addConverterFactory(MoshiConverterFactory.create())
    .build()


interface ApiServer {
    @POST(ADD_TRANSACTIONS)
    suspend fun login(@Body DataTranfer: Transactionp2pDTO): Tranferp2pResponse
}
object BanplusApi {
    val retrofitService: ApiServer by lazy {
        retrofit.create(ApiServer::class.java)
    }
}