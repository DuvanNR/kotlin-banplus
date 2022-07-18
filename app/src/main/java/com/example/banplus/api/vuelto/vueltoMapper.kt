package com.example.banplus.api.vuelto

import com.example.banplus.api.vuelto.response.Tranferp2pResponse
import com.example.banplus.api.vuelto.response.Tranferp2pResponse.Pago


class VueltoDtoMapper {
    fun converterPagoDTOaPago(resp: Tranferp2pResponse): Tranferp2pResponse {
        return resp
    }
}
