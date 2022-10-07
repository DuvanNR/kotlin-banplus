package com.example.banplus.repository

import androidx.lifecycle.LiveData
import com.example.banplus._interface.iTransaction
import com.example.banplus.api.ApiServer
import com.example.banplus.api.vuelto.dto.MapperBodyVuelto
import com.example.banplus.db.schema.*
import com.example.banplus.utils.getDatetime
import retrofit2.HttpException
import java.net.UnknownHostException
import javax.inject.Inject

interface VueltoRespository {
    suspend fun emitTransaction(
        e: iTransaction, commerce: Commerce
    ): iTransaction

    fun getTotalTransfer(): LiveData<TransCount>
    fun getAllTransaccion(): LiveData<List<Transaction>>
    abstract fun deleteYesterdayInvoice(): Unit
}

class VueltoRespositoryImp @Inject constructor(
    private val dataSource: ApiServer,
    private val transactionDao: TransactionDao,
) : VueltoRespository {
    override suspend fun emitTransaction(
        e: iTransaction, commerce: Commerce
    ): iTransaction {
        val mapperBody = MapperBodyVuelto()
        val body = mapperBody.converToResBodyaTransctionApi(e, commerce)
        val data = getDatetime()
        try {
            val resp = dataSource.emitTransaction(body)
            println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx${resp}")
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

                return iTransaction(
                    tipo = e.tipo,
                    message = "${resp.msRsB.pago?.descripcionRespuesta}",
                    banco = e.banco,
                    cedula = e.cedula,
                    fecha = data.fecha,
                    hora = data.hora,
                    monto = e.monto,
                    nameBanco = e.nameBanco,
                    ref = "${resp.msRsB.pago?.referenciaRespuesta}",
                    telefono = e.telefono,
                    state = true
                )
            }
            return iTransaction(
                tipo = e.tipo,
                message = "${resp.msRsB.pago?.descripcionRespuesta}",
                banco = e.banco,
                cedula = e.cedula,
                fecha = data.fecha,
                hora = data.hora,
                monto = e.monto,
                nameBanco = e.nameBanco,
                ref = "${resp.msRsB.pago?.referenciaRespuesta}",
                telefono = e.telefono,
                state = false
            )

        } catch (err: UnknownHostException) {
            return iTransaction(
                tipo = e.tipo,
                message = "${err.message}",
                banco = e.banco,
                cedula = e.cedula,
                fecha = data.fecha,
                hora = data.hora,
                monto = e.monto,
                nameBanco = e.nameBanco,
                ref = "xxxxxxxxxxxx",
                telefono = e.telefono,
                state = false
            )
            println("Errorrrrrrrrrr01 _____________${e.message}")
        } catch (err: HttpException) {
            println("Errorrrrrrrrrr02 _____________${e.message}")
            return iTransaction(
                tipo = e.tipo,
                message = "${err.message}",
                banco = e.banco,
                cedula = e.cedula,
                fecha = data.fecha,
                hora = data.hora,
                monto = e.monto,
                nameBanco = e.nameBanco,
                ref = "xxxxxxxxxxxx",
                telefono = e.telefono,
                state = false
            )
        } catch (err: Exception) {
            println("Errorrrrrrrrrr03 _____________${e.message}")
            return iTransaction(
                tipo = e.tipo,
                message = "${err.message}",
                banco = e.banco,
                cedula = e.cedula,
                fecha = data.fecha,
                hora = data.hora,
                monto = e.monto,
                nameBanco = e.nameBanco,
                ref = "xxxxxxxxxxxx",
                telefono = e.telefono,
                state = false
            )
        }
    }

    override fun getTotalTransfer(): LiveData<TransCount> {
        return transactionDao.countResult()
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


