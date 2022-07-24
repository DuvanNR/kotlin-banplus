package com.example.banplus.api.vuelto.dto

import com.example.banplus._interface.iTransaction
import com.example.banplus.db.schema.Commerce

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
           checkSUM = "686375BA11F93A3D99376DD5B8F2C1F8B402 B0AA14278D03211ABA4A847925E928469DEE 7CFB8D5D634DC6F28F614D15A70D1B67C291 77450DE73958E42F0B1E"
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