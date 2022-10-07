package com.example.banplus.context

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


object LoadingContext : ViewModel() {
    val isLoading: MutableState<Boolean> = mutableStateOf(false)

    @Synchronized
    fun endproces() {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                delay(900000)
            }
            closeDialog()
        }
    }

    @Synchronized
    fun closeDialog() {
        isLoading.value = false
    }


    @Synchronized
    fun StartLoading() {
        isLoading.value = true
    }

    @Synchronized
    fun CloseDelay() {
        StartLoading()
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                delay(1000)
            }
            closeDialog()
            LoginContext.CloseCardLogin()
        }
    }
}