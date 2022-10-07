package com.example.banplus.viewmodel

//import android.content.Context
//import androidx.compose.runtime.MutableState
//import androidx.compose.runtime.mutableStateOf
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.banplus._interface.iTransaction
//import com.example.banplus.context.TransactionContext
//import com.example.banplus.template.FormData
//import com.example.banplus.utils.GoToActivity
//import com.example.banplus.utils.addDecimals
//import com.example.banplus.utils.getDatetime
//import com.example.banplus.utils.iNameActivity
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.delay
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.withContext
//import javax.inject.Inject
//
//enum class eNameCard {
//    CARD_TRANSFER, CARD_VALUE, CARD_TRANSFER_TRUE, CARD_TRANSFER_FALSE
//}
//
//
//@HiltViewModel
//class LoadingViewModel @Inject constructor() : ViewModel() {
//    var open = MutableLiveData<Boolean>()
//    var _nameCard: MutableState<eNameCard> =
//        mutableStateOf(eNameCard.CARD_TRANSFER)
//
//    fun startThread(context: Context) {
//        viewModelScope.launch {
//            withContext(Dispatchers.Default) {
//                delay(7000)
//            }
//            closeDialog(context)
//        }
//    }
//
//    private fun closeDialog(context: Context) {
//        open.value = false
//        GoToActivity(iNameActivity.CARD, context, finish = true)
//    }
//
//    fun StartLoading(form: FormData, nameCard: eNameCard) {
//        println("xxxxxxxxxxxxxxxxxxxxxxxxxxxx ${form}")
//        val date = getDatetime()
//        TransactionContext.setTransaction(
//            iTransaction(
//                tipo = form.typeDocument.key,
//                cedula = form.numDocument,
//                telefono = form.telefono,
//                banco = form.banco.key,
//                monto = addDecimals(form.monto),
//                nameBanco = form.banco.title,
//                ref = "xxxx",
//                hora = date.hora,
//                fecha = date.fecha,
//                message = ""
//            )
//        )
//        open.value = true
//        _nameCard.value = nameCard
//    }
//}