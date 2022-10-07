package com.example.banplus.viewmodel

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.banplus._interface.iTransaction
import com.example.banplus.api.ApiResponseStatus
import com.example.banplus.api.vuelto.response.Tranferp2pResponse
import com.example.banplus.context.LoadingContext
import com.example.banplus.context.TransactionContext
import com.example.banplus.db.schema.Commerce
import com.example.banplus.db.schema.TransCount
import com.example.banplus.db.schema.Transaction
import com.example.banplus.repository.VueltoRespository
import com.example.banplus.utils.GoToActivity
import com.example.banplus.utils.iNameActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VueltoViewModel @Inject constructor(
    private val VueltoRepo: VueltoRespository,
) : ViewModel() {
    private val _isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }
    val isLoading: LiveData<Boolean> get() = _isLoading
    val allTransaction: LiveData<List<Transaction>> by lazy {
        VueltoRepo.getAllTransaccion()
    }

    val TotalTrans: LiveData<TransCount> by lazy {
        VueltoRepo.getTotalTransfer()
    }
    var vueltoR = mutableStateOf<Tranferp2pResponse?>(null)
        private set
    var status = mutableStateOf<ApiResponseStatus<Tranferp2pResponse>?>(null)
        private set

    fun EmitPago(e: iTransaction, commerce: Commerce, context: Context) {
        viewModelScope.launch {
            LoadingContext.StartLoading()
            status.value = ApiResponseStatus.Loading()
            val aa = VueltoRepo.emitTransaction(e, commerce)
            TransactionContext.setTransaction(aa)
            GoToActivity(iNameActivity.CARD, context, true)
            LoadingContext.closeDialog()

        }
    }

    fun deleteYesterdayInvoice() {
        if (_isLoading.value == false) {
            viewModelScope.launch(Dispatchers.IO) {
                _isLoading.postValue(true)

                VueltoRepo.deleteYesterdayInvoice()


                _isLoading.postValue(false)
            }
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