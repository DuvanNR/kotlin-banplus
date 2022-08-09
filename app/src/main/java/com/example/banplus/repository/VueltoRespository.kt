package com.example.banplus.repository

import androidx.lifecycle.LiveData
import com.example.banplus._interface.iTransaction
import com.example.banplus.api.ApiResponseStatus
import com.example.banplus.api.ApiServer
import com.example.banplus.api.vuelto.dto.Transactionp2pDTO
import com.example.banplus.api.makeNetworkCall
import com.example.banplus.api.reportes.ReporteMapper
import com.example.banplus.api.reportes.response.ReportesResponse
import com.example.banplus.api.vuelto.VueltoDtoMapper
import com.example.banplus.api.vuelto.dto.MapperBodyVuelto
import com.example.banplus.api.vuelto.response.Tranferp2pResponse
import com.example.banplus.db.schema.*
import com.example.banplus.utils.getDatetime
import com.example.banplus.utils.getDay
import javax.inject.Inject

interface VueltoRespository {
    suspend fun emitTransaction(
        e: iTransaction,
        commerce: Commerce
    ): ApiResponseStatus<Tranferp2pResponse>

    fun getTotalTransfer(): LiveData<TransCount>
    fun getAllTransaccion(): LiveData<List<Transaction>>
    abstract fun deleteYesterdayInvoice(): Unit
}

class VueltoRespositoryImp @Inject constructor(
    private val dataSource: ApiServer,
    private val transactionDao: TransactionDao,
) : VueltoRespository {
    override suspend fun emitTransaction(
        e: iTransaction,
        commerce: Commerce
    ): ApiResponseStatus<Tranferp2pResponse> = makeNetworkCall {
        val mapperBody = MapperBodyVuelto()
        val body = mapperBody.converToResBodyaTransctionApi(e, commerce)
        val resp = dataSource.emitTransaction(body)
        val vueltoMapper = VueltoDtoMapper()
        val data = getDatetime()
        if (resp.msRsH?.codigo == "1") {
            transactionDao.insert(
                Transaction(
                    tipo = e.tipo,
                    message = "${resp.msRsB.pago?.descripcionRespuesta}",
                    banco = e.banco,
                    cedula = e.cedula,
                    fecha = data.fecha,
                    hora = data.hora,
                    monto = e.monto.toDouble(),
                    nameBanco = e.nameBanco,
                    ref = "${resp.msRsB.pago?.referenciaRespuesta}",
                    telefono = e.telefono,
                )
            )
        }
        vueltoMapper.converterPagoDTOaPago(resp)
    }

    override fun getTotalTransfer(): LiveData<TransCount> {
        return  transactionDao.countResult()
    }

    override fun getAllTransaccion(): LiveData<List<Transaction>> {
        return transactionDao.getAll()
    }

    override fun deleteYesterdayInvoice() {
        val today = getDatetime()
        return transactionDao.deleteInvoiceNotToday(today.fecha)
        println("ultima capa de la logica ${today}")
    }


}


