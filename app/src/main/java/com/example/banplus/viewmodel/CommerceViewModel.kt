package com.example.banplus.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.banplus.db.schema.Commerce
import com.example.banplus.repository.CommerceRespository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommerceViewModel @Inject constructor(
    private val commerceDao: CommerceRespository
): ViewModel()  {
    private val _isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }
    val isLoading: LiveData<Boolean> get() = _isLoading
    val commerce: LiveData<Commerce> by lazy {
        commerceDao.getCommerceById(1)
    }
    val commerces: LiveData<List<Commerce>> by lazy {
        commerceDao.getAll()
    }
    fun addCommerce(data: Commerce) {
        if(_isLoading.value == false ) {
            viewModelScope.launch(Dispatchers.IO) {
                _isLoading.postValue(true)
                commerceDao.addCommerce(data)
                _isLoading.postValue(false)
            }
        }

    }

    fun GetAllCommerce(data: Commerce) {
        if(_isLoading.value == false ) {
            viewModelScope.launch(Dispatchers.IO) {
                _isLoading.postValue(true)
                commerceDao.getAll()
                _isLoading.postValue(false)
            }
        }

    }

}