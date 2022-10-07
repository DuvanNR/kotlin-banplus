package com.example.banplus.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.banplus.api.ApiResponseStatus
import com.example.banplus.api.ApiServiceInterceptor
import com.example.banplus.api.backend.dto.LoginDTO
import com.example.banplus.api.backend.response.InfoTerminalResponse
import com.example.banplus.api.backend.response.LoginResponse
import com.example.banplus.config.PASS_BACKEND
import com.example.banplus.config.USER_BACKEND
import com.example.banplus.context.LoadingContext
import com.example.banplus.repository.BackendRespository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BackendViewModel @Inject constructor(
    private val backendRespository: BackendRespository
) : ViewModel() {
    var resp = mutableStateOf<LoginResponse>(LoginResponse())
        private set
    var InfoTerminal = mutableStateOf<InfoTerminalResponse>(InfoTerminalResponse())
        private set

    var status = mutableStateOf<ApiResponseStatus<Any>?>(null)
        private set

    init {
        login(
            LoginDTO(
                USER_BACKEND, PASS_BACKEND
            )
        )
    }

    fun getSerial(serial: String) {
        viewModelScope.launch {
            LoadingContext.StartLoading()
            status.value = ApiResponseStatus.Loading()
            handleResponseTerminalStatus(
                backendRespository.getInfoTerminal(serial)
            )
            LoadingContext.closeDialog()
        }

    }

    fun login(data: LoginDTO) {
        viewModelScope.launch {
            LoadingContext.StartLoading()
            status.value = ApiResponseStatus.Loading()
            handleResponseStatus(
                backendRespository.login(data)
            )
            LoadingContext.closeDialog()
        }
    }

    //
//
//    fun onResetApiResponse() {
//        status.value = null
//    }
    @Suppress("UNCHE")
    private fun handleResponseStatus(apiResponseStatus: ApiResponseStatus<LoginResponse>) {
        if (apiResponseStatus is ApiResponseStatus.Success) {
            ApiServiceInterceptor.setSessionToken(apiResponseStatus.data.access)
            resp.value = apiResponseStatus.data
        }
        status.value = apiResponseStatus as ApiResponseStatus<Any>
    }

    @Suppress("UNCHETerminal REsponse")
    private fun handleResponseTerminalStatus(apiResponseStatus: ApiResponseStatus<InfoTerminalResponse>) {
        if (apiResponseStatus is ApiResponseStatus.Success) {
            InfoTerminal.value = apiResponseStatus.data
        }

        status.value = apiResponseStatus as ApiResponseStatus<Any>

    }
}
