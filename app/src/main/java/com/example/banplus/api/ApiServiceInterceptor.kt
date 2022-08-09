package com.example.banplus.api
import  okhttp3.Interceptor
import okhttp3.Response

object ApiServiceInterceptor: Interceptor {
    const val NEEDS_AUTH_HEADER_KEY = "BACKEND"
    private var sessionToken:String? = null
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestBuilder = request.newBuilder()
        if(request.header(NEEDS_AUTH_HEADER_KEY) != null) {
            var token = "Bearer $sessionToken"
            println("token reset $token")
            if(sessionToken != null) {
                requestBuilder.addHeader("Authorization", token )
            }
        }
        return chain.proceed(requestBuilder.build())
    }

    fun setSessionToken(access: String?) {
        this.sessionToken = access
    }

}