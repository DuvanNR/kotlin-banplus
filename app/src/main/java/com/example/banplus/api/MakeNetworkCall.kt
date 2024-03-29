package com.example.banplus.api

import com.example.banplus.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.UnknownHostException

private const val UNAUTHORIZED = 401
private const val NOASIGNADO = 403

suspend fun <T> makeNetworkCall(
    call: suspend () -> T
): ApiResponseStatus<T> {
    return withContext(Dispatchers.IO) {
        try {
            ApiResponseStatus.Success(call())
        } catch (e: UnknownHostException) {
            println("Errorrrrrrrrrr01 _____________${e.message}")
            ApiResponseStatus.Error(R.string.unknown_host_exception_error)
        } catch (e: HttpException) {
            println("Errorrrrrrrrrr02 _____________${e.message}")
            val errorMessage = when (e.code()) {
                UNAUTHORIZED -> R.string.your_credentials_are_incorrect
                NOASIGNADO -> R.string.error_403
                else -> R.string.unknown_error
            }

            ApiResponseStatus.Error(errorMessage)
        } catch (e: Exception) {
            println("Errorrrrrrrrrr03 _____________${e.message}")
            println("Errorrrrrrrrrr03 _____________${e}")
            val errorMessage = when (e.message) {
                "Unauthorized" -> R.string.your_credentials_are_incorrect
                "sign_up_error" -> R.string.error_sign_up
                "sign_in_error" -> R.string.error_sign_in
                "user_already_exists" -> R.string.user_already_exists
                "Failed to connect to /54.94.83.180:8002" -> R.string.SINCONEXION
                else -> R.string.unknown_error
            }
            ApiResponseStatus.Error(errorMessage)
        }

    }

}
