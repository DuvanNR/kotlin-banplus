package com.example.banplus.repository

import androidx.lifecycle.LiveData
import com.example.banplus.db.schema.Commerce
import com.example.banplus.db.schema.CommerceDao

import javax.inject.Inject


interface CommerceRespository {
    fun getCommerceById(id: Int):LiveData<Commerce>
    fun getAll(): LiveData<List<Commerce>>
    abstract fun addCommerce(data: Commerce): Unit
}

class CommerceRespositoryImp @Inject constructor(
    private val commerceDao: CommerceDao
) : CommerceRespository {

    override fun getCommerceById(id: Int): LiveData<Commerce> {
     return commerceDao.getOneById(id)
    }

    override fun getAll(): LiveData<List<Commerce>> {
        return commerceDao.getAll()
    }

    override fun addCommerce(data: Commerce) {
        commerceDao.insert(data)
    }
}
