package com.example.banplus.viewmodel

import androidx.lifecycle.ViewModel
import com.example.banplus.api.vuelto.dto.BodyDTO
import androidx.compose.runtime.*
import androidx.lifecycle.viewModelScope
import com.example.banplus.api.ApiResponseStatus
import com.example.banplus.api.vuelto.dto.MapperBodyVuelto
import com.example.banplus.api.vuelto.response.PagoDTO
import com.example.banplus.repository.VueltoRespository
import kotlinx.coroutines.launch

class VueltoViewModel:ViewModel() {
    val _result = mutableStateOf<PagoDTO?>(null)
    val _status = mutableStateOf<ApiResponseStatus<PagoDTO>?>(null)
    private val VueltoBodyRepository = VueltoRespository()
    fun EmitTransaccion(tipo: String, cedula: String, telefono: String, monto: Float, banco: String) {
        viewModelScope.launch {
            val mapperBody = MapperBodyVuelto()
            val body = mapperBody.converToResBodyaTransctionApi(tipo,cedula,telefono,banco,monto)
            _status.value = ApiResponseStatus.Loading()
            handleResponseStatus(VueltoBodyRepository.emitTransaction(body))
        }
    }
    private fun handleResponseStatus(apiResponseStatus: ApiResponseStatus<PagoDTO>) {
        if (apiResponseStatus is ApiResponseStatus.Success) {

            _result.value = apiResponseStatus.data
        }
        _status.value = apiResponseStatus
    }
}