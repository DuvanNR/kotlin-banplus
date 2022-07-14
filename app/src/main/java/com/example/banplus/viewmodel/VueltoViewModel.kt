package com.example.banplus.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.banplus.api.ApiResponseStatus
import com.example.banplus.api.vuelto.dto.MapperBodyVuelto
import com.example.banplus.api.vuelto.response.PagoDTO
import com.example.banplus.repository.VueltoRespository
import kotlinx.coroutines.launch

class VueltoViewModel: ViewModel() {
    var vueltoR = mutableStateOf<PagoDTO?>(null)
        private set
    var status = mutableStateOf<ApiResponseStatus<PagoDTO>?>(null)
        private  set

    private val authRepository = VueltoRespository()

    fun EmitPago(tipo:String, cedula: String, telefono:String,banco: String, monto:Float) {
        viewModelScope.launch {
            status.value = ApiResponseStatus.Loading()
            val mapperBody = MapperBodyVuelto()
            val body = mapperBody.converToResBodyaTransctionApi(tipo, cedula,telefono=telefono, banco=banco,monto=monto)
            handleResponseStatus(authRepository.emitTransaction(body))
        }
    }
    private fun handleResponseStatus(apiResponseStatus: ApiResponseStatus<PagoDTO>) {
        if (apiResponseStatus is ApiResponseStatus.Success) {
            vueltoR.value = apiResponseStatus.data
        }
        status.value = apiResponseStatus
    }
}