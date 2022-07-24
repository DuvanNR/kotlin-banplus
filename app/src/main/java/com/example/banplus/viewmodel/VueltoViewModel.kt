package com.example.banplus.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.banplus._interface.iTransaction
import com.example.banplus.api.ApiResponseStatus
import com.example.banplus.api.vuelto.dto.MapperBodyVuelto
import com.example.banplus.api.vuelto.response.Tranferp2pResponse
import com.example.banplus.db.schema.Commerce
import com.example.banplus.repository.VueltoRespository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VueltoViewModel @Inject constructor(
   private val VueltoRepo: VueltoRespository
): ViewModel() {
    var vueltoR = mutableStateOf<Tranferp2pResponse?>(null)
        private set
    var status = mutableStateOf<ApiResponseStatus<Tranferp2pResponse>?>(null)
        private set
    fun EmitPago(e:iTransaction,commerce: Commerce) {
        viewModelScope.launch {
            status.value = ApiResponseStatus.Loading()
            handleResponseStatus(
                VueltoRepo.emitTransaction(e,commerce)
            )
        }
    }
    fun onResetApiResponse() {
        vueltoR.value = null
        status.value = null
    }
    private fun handleResponseStatus(apiResponseStatus: ApiResponseStatus<Tranferp2pResponse>) {
        if (apiResponseStatus is ApiResponseStatus.Success) {
            vueltoR.value = apiResponseStatus.data
        }
        status.value = apiResponseStatus
    }
}