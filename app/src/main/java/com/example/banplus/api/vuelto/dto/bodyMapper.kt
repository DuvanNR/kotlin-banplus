package com.example.banplus.api.vuelto.dto

class MapperBodyVuelto {
    private fun bodyDimple(tipo: String, cedula: String, telefono: String, banco: String, monto: Float):BodyDTO {
       return BodyDTO(
           tipoDocumentoPagador = tipo,
           telefonoPagador = cedula,
           cedulaPagador =  telefono,
           monto = monto,
           bancoReceptor = banco,
           telefonoReceptor = "584241127426",
           tipoDocumentoReceptor ="v",
           cedulaReceptor = "7950354",
           checkSUM="686375BA11F93A3D99376DD5B8F2C1F8B402 B0AA14278D03211ABA4A847925E928469DEE 7CFB8D5D634DC6F28F614D15A70D1B67C291 77450DE73958E42F0B1E"
       )

    }
    private fun headerSimple():HeaderDTO{

        return HeaderDTO(
            idServicio= 19990054,
        ipUsuario = "::1",
        idFuenteAutenticacion= 9,
        ipServer = "",
        ipMidServer = "",
        codigoAplicacion= 9,
        idioma= 1
        )
    }



    fun converToResBodyaTransctionApi(tipo: String, cedula: String, telefono: String, banco: String, monto: Float):Transactionp2pDTO {
        val body = bodyDimple(tipo = tipo, cedula = cedula, telefono = telefono, banco= banco, monto= monto )
         return Transactionp2pDTO(msRqB = body , msRqH =headerSimple())
    }

}