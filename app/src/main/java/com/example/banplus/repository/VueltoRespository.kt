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
import javax.inject.Inject

interface VueltoRespository {
    suspend fun emitTransaction(
        e: iTransaction,
        commerce: Commerce
    ): ApiResponseStatus<Tranferp2pResponse>

    fun getTotalTransfer(): LiveData<TransCount>
    fun getAllTransaccion(): LiveData<List<Transaction>>
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
        "fin_____estamos llegando asta aqui_______________________________data "
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
            "fin____________________________________data "
        }
        vueltoMapper.converterPagoDTOaPago(resp)
    }

    override fun getTotalTransfer(): LiveData<TransCount> {
        return  transactionDao.countResult()
    }

    override fun getAllTransaccion(): LiveData<List<Transaction>> {
        return transactionDao.getAll()
    }


}


