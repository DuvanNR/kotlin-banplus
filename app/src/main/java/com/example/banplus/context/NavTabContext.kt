package com.example.banplus.context

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


object NavTabContext : ViewModel() {
    val numTab: MutableState<Int> = mutableStateOf(0)


    @Synchronized
    fun NextTab() {
        numTab.value = numTab.value + 1
    }


    @Synchronized
    fun LastTab() {
        numTab.value = numTab.value - 1
    }

    @Synchronized
    fun resetNumTab() {
        numTab.value = 0
    }

}