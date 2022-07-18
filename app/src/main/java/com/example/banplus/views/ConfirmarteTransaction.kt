package com.example.banplus.views

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
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
import com.example.banplus.navigation.PathRouter
import com.example.banplus.ui.theme.color_fontbtn
import com.example.banplus.viewmodel.VueltoViewModel

@Composable
fun ConfirmarteTransaction (
    navController: NavController,
    resp: iTransaction,
    viewModelVuelto: VueltoViewModel,
    status: ApiResponseStatus<Tranferp2pResponse>?,
    onErrorDialog: () -> Unit,
    onEventExito:(String,String,String,String,String) -> Unit
){
    var value = viewModelVuelto.vueltoR.value
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

                viewModelVuelto.EmitPago(tipo = resp.tipo, cedula = resp.cedula, telefono = resp.telefono, banco= resp.banco,monto=resp.monto)
            })
        }
    }
    if (status is ApiResponseStatus.Loading) {
        LoadingWheel()
    }else if(status is ApiResponseStatus.Error) {
        errorDialog(description = stringResource(id = status.messageId), onDialogDismiss = onErrorDialog)
    }
    if(value != null) {
        println(value.msRsH)
        when(value.msRsH?.codigo) {
            "1" -> onEventExito(resp.tipo , resp.cedula , resp.telefono, resp.banco, resp.monto)
            else -> errorDialog(description = "${value.msRsH?.descripcionError}", onDialogDismiss = onErrorDialog)
        }
    }

}
@Composable
private fun body(resp: iTransaction, onClickCancelar: () -> Unit, onClickEmitTransaction: () -> Unit) {
    var enabled by remember { mutableStateOf(true) }
    Box(
        modifier = Modifier
            .padding(top=44.dp)
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
                    onClickEmitTransaction() },
                ico = painterResource(id = R.drawable.ic_next),
                modifier = Modifier
                    .padding(4.dp)
                    .height(49.dp)
                    .width(240.dp)
            )
            BtnNext(
                text = "Cancelar",

                onClick =  {onClickCancelar()},
                ico = painterResource(id = R.drawable.ic_circle_error),
                background= color_fontbtn,
                modifier = Modifier
                    .padding(4.dp)
                    .height(49.dp)
                    .width(240.dp)
            )

        }
    }

}