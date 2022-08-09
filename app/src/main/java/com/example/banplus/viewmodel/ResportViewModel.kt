package com.example.banplus.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.banplus.api.ApiResponseStatus
import com.example.banplus.api.reportes.response.ReportesResponse
import com.example.banplus.repository.ReportesRespository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReportesViewModel @Inject constructor(
    private val reportesRepositoryImp: ReportesRespository
): ViewModel()  {
    var resp = mutableStateOf<List<ReportesResponse.Movimiento>>(listOf())
        private set
    var status = mutableStateOf<ApiResponseStatus<Any>?>(null)
        private set
    init {
        GetReportes()
    }
    fun GetReportes() {
        viewModelScope.launch {
            status.value = ApiResponseStatus.Loading()
            handleResponseStatus(
                reportesRepositoryImp.GetReportes()
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