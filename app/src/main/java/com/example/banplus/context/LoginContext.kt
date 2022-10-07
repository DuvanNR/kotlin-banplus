package com.example.banplus.context

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.banplus.utils.GoToActivity
import com.example.banplus.utils.iNameActivity

//
//enum class eActivity {
//    CARD_TRANSFER, CARD_VALUE, CARD_TRANSFER_TRUE, CARD_TRANSFER_FALSE
//}

object LoginContext : ViewModel() {
    val isLogin: MutableState<Boolean> = mutableStateOf(false)
    val LoginIntento: MutableState<Int> = mutableStateOf(0)
    val function: MutableState<() -> Unit> = mutableStateOf({})


    @Synchronized
    fun startCardLogin(event: () -> Unit) {
        isLogin.value = true
        function.value = event
    }

    @Synchronized
    fun CloseCardLogin(state: Boolean = false) {
        if (state) {
            function.value()
        }
        isLogin.value = false
        LoginIntento.value = 0
    }

    @Synchronized
    fun addLoginIntneto(context: Context) {
        LoginIntento.value = LoginIntento.value + 1
        if (LoginIntento.value == 4) {
            CloseCardLogin()
            LoadingContext.StartLoading()
            GoToActivity(iNameActivity.MAIN, context, true)

        }
    }

}