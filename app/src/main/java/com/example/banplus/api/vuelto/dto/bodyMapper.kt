package com.example.banplus.api.vuelto.dto

import com.example.banplus._interface.iTransaction
import com.example.banplus.db.schema.Commerce
import com.example.banplus.utils.getChecksum

class MapperBodyVuelto {
    private fun MsRqB(re:iTransaction, pag: Commerce): Transactionp2pDTO.MsRqB {
       return Transactionp2pDTO.MsRqB(
           tipoDocumentoPagador = pag.tipo,
           telefonoPagador = pag.telefono,
           cedulaPagador = pag.rif,
           monto = re.monto,
           bancoReceptor = re.banco,
           telefonoReceptor = re.telefono,
           tipoDocumentoReceptor = re.tipo,
           cedulaReceptor = re.cedula,
           checkSUM = getChecksum(re,pag)
       )

    }
    private fun MsRqH(): Transactionp2pDTO.MsRqH {

        return Transactionp2pDTO.MsRqH(
            idServicio = 19990054,
            ipUsuario = "1",
            idFuenteAutenticacion = 9,
            ipServer = "11",
            ipMidServer = "11",
            codigoAplicacion = 9,
            idioma = 1
        )
    }



    fun converToResBodyaTransctionApi(e:iTransaction, commerce: Commerce):Transactionp2pDTO {
        val body = MsRqB(re=e, pag=commerce)
        val header = MsRqH()
         return Transactionp2pDTO(msRqB = body , msRqH = header)
    }

}