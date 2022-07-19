package com.example.banplus.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.banplus.api.ApiResponseStatus
import com.example.banplus.api.reportes.response.ReportesResponse
import com.example.banplus.repository.ReportesRespository
import kotlinx.coroutines.launch

class ReportesViewModel : ViewModel() {
    var resp = mutableStateOf<List<ReportesResponse.Movimiento>>(listOf())
        private set
    var status = mutableStateOf<ApiResponseStatus<Any>?>(null)
        private set
    private val authRepository = ReportesRespository()
    init {
        GetReportes()
    }
    fun GetReportes() {
        viewModelScope.launch {
            status.value = ApiResponseStatus.Loading()
            handleResponseStatus(
                authRepository.GetReportes()
            )
        }
    }

    fun onResetApiResponse() {
        status.value = null
    }
    @Suppress("UNCHE")
    private fun handleResponseStatus(apiResponseStatus: ApiResponseStatus<List<ReportesResponse.Movimiento>>) {
        if (apiResponseStatus is ApiResponseStatus.Success) {
            resp.value = apiResponseStatus.data
        }
        status.value = apiResponseStatus as ApiResponseStatus<Any>
    }
}