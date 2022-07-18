package com.example.banplus.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
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
import com.example.banplus.ui.theme.color_fontbtn

@Composable
fun ConfirmarteTransaction (
    navController: NavController,
    resp: iTransaction,
    status: ApiResponseStatus<Tranferp2pResponse.Pago>? = null,
    onClickViewModel: (
        cedula: String,
        cell: String,
        banco: String,
        tipo: String,
        monto:String
    ) -> Unit
){
    Scaffold() {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            HeaderInit(icon = R.drawable.ic_recurso_4)
            body(resp)
        }
    }
    if (status is ApiResponseStatus.Loading) {
        LoadingWheel()
    }else if( status is ApiResponseStatus.Error ) {
        errorDialog(status = status, onDialogDismiss = {})
    }
}
@Composable
private fun body(resp: iTransaction) {
    Box(
        modifier = Modifier
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
                onClick = { },
                ico = painterResource(id = R.drawable.ic_next),
                modifier = Modifier
                    .padding(4.dp)
                    .height(49.dp)
                    .width(240.dp)
            )
            BtnNext(
                text = "Cancelar",
                onClick =  {},
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