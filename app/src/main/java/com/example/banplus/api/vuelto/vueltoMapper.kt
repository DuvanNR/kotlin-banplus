package com.example.banplus.api.vuelto

import com.example.banplus.api.vuelto.response.Tranferp2pResponse.Pago


class VueltoDtoMapper {
    fun converterPagoDTOaPago(pago: Pago?): Pago {
        println(pago)
        return Pago(
            fechaRespuesta = pago?.fechaRespuesta,
            referenciaRespuesta = pago?.referenciaRespuesta,
            codigoRespuesta = pago?.codigoRespuesta,
            descripcionRespuesta = pago?.descripcionRespuesta,
        )
    }
}
