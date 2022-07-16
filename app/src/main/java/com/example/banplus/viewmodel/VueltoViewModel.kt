package com.example.banplus.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.banplus.api.ApiResponseStatus
import com.example.banplus.api.vuelto.dto.MapperBodyVuelto
import com.example.banplus.api.vuelto.response.Tranferp2pResponse
import com.example.banplus.repository.VueltoRespository
import kotlinx.coroutines.launch

class VueltoViewModel : ViewModel() {
    var vueltoR = mutableStateOf<Tranferp2pResponse.Pago?>(null)
        private set
    var status = mutableStateOf<ApiResponseStatus<Tranferp2pResponse.Pago>?>(null)
        private set

    private val authRepository = VueltoRespository()

    fun EmitPago(tipo: String, cedula: String, telefono: String, banco: String, monto: String) {
        viewModelScope.launch {
            status.value = ApiResponseStatus.Loading()

            handleResponseStatus(
                authRepository.emitTransaction(
                    tipo = tipo,
                    cedula = cedula,
                    telefono = telefono,
                    banco = banco,
                    monto = monto
                )
            )
        }
    }

    private fun handleResponseStatus(apiResponseStatus: ApiResponseStatus<Tranferp2pResponse.Pago>) {
        if (apiResponseStatus is ApiResponseStatus.Success) {
            vueltoR.value = apiResponseStatus.data
        }
        status.value = apiResponseStatus
    }
}