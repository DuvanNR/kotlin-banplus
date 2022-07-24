package com.example.banplus.views

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.banplus.R
import com.example.banplus._interface.iTransaction
import com.example.banplus.api.ApiResponseStatus
import com.example.banplus.api.vuelto.response.Tranferp2pResponse
import com.example.banplus.component.BtnNext
import com.example.banplus.component.LoadingWheel
import com.example.banplus.component.cardsAlert
import com.example.banplus.component.errorDialog
import com.example.banplus.component.header.HeaderInit
import com.example.banplus.db.schema.Commerce
import com.example.banplus.navigation.PathRouter
import com.example.banplus.ui.theme.color_fontbtn
import com.example.banplus.utils.addDecimals
import com.example.banplus.utils.getDatetime
import com.example.banplus.viewmodel.CommerceViewModel
import com.example.banplus.viewmodel.VueltoViewModel

@Composable
fun ConfirmarteTransaction(
    viewModel: VueltoViewModel = hiltViewModel(),
    viewModelCommerce: CommerceViewModel = hiltViewModel(),
    navController: NavController,
    resp: iTransaction,
    onEventExito: (iTransaction) -> Unit
) {
    val commerce = viewModelCommerce.commerce.observeAsState(Commerce("", "", "", ""))
    var value = viewModel.vueltoR.value
    val status: ApiResponseStatus<Tranferp2pResponse>? = viewModel.status.value
    Scaffold() {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            HeaderInit(icon = R.drawable.ic_recurso_4)
            body(resp, onClickCancelar = {
                navController.navigate(PathRouter.HomeRoute.route)
            }, onClickEmitTransaction = {
                println(resp.monto)
                viewModel.EmitPago(
                    iTransaction(
                        tipo = resp.tipo,
                        cedula = resp.cedula,
                        telefono = resp.telefono,
                        banco = resp.banco,
                        monto = resp.monto
                    ),
                    commerce = commerce.value
                )
            })
        }
    }
    if (status is ApiResponseStatus.Loading) {
        LoadingWheel()
    } else if (status is ApiResponseStatus.Error) {
        errorDialog(description = stringResource(id = status.messageId), onDialogDismiss = {
            viewModel.onResetApiResponse()
            navController.navigate(PathRouter.HomeRoute.route)
        })
    }
    if (value != null) {
        val date = getDatetime()
        println("${value.msRsB.pago}")
        when (value.msRsH?.codigo) {
            "1" -> onEventExito(
                iTransaction(
                    tipo = resp.tipo,
                    cedula = resp.cedula,
                    telefono = resp.telefono,
                    banco = resp.banco,
                    monto = resp.monto,
                    nameBanco = resp.nameBanco,
                    hora = date.hora,
                    fecha = date.fecha,
                    ref = "${value.msRsB.pago?.referenciaRespuesta}"
                )
            )
            else -> errorDialog(
                description = "${value.msRsH?.descripcionError}",
                onDialogDismiss = {
                    viewModel.onResetApiResponse()
                    navController.navigate(PathRouter.HomeRoute.route)
                })
        }
    }

}

@Composable
private fun body(
    resp: iTransaction,
    onClickCancelar: () -> Unit,
    onClickEmitTransaction: () -> Unit
) {
    var enabled by remember { mutableStateOf(true) }
    Box(
        modifier = Modifier
            .padding(top = 44.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        cardsAlert(rif = false, title = "Confirmar Operación", iData = resp)
        Column(
            modifier = Modifier.align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            BtnNext(
                text = "Continuar",
                enabled = enabled,
                onClick = {
                    enabled = !enabled
                    onClickEmitTransaction()
                },
                ico = painterResource(id = R.drawable.ic_next),
                modifier = Modifier
                    .padding(4.dp)
                    .height(49.dp)
                    .width(240.dp)
            )
            BtnNext(
                text = "Cancelar",

                onClick = { onClickCancelar() },
                ico = painterResource(id = R.drawable.ic_circle_error),
                background = color_fontbtn,
                modifier = Modifier
                    .padding(4.dp)
                    .height(49.dp)
                    .width(240.dp)
            )

        }
    }

}