package com.example.banplus.context

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.banplus._interface.iTransaction

object TransactionContext {
    private val _Transaction: MutableState<iTransaction> = mutableStateOf(iTransaction())

    @Synchronized
    fun getTransaction(): iTransaction {
        return _Transaction.value
    }

    @Synchronized
    fun setTransaction(data: iTransaction) {
        _Transaction.value = data
    }

    @Synchronized
    fun reset() {
        _Transaction.value = iTransaction()
    }
}