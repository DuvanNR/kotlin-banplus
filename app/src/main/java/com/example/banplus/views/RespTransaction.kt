package com.example.banplus.views

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.banplus.R
import com.example.banplus._interface.iTransaction
import com.example.banplus.component.BtnNext
import com.example.banplus.component.cardsAlert
import com.example.banplus.component.header.HeaderInit
import com.example.banplus.db.schema.Commerce
import com.example.banplus.ui.theme.color_fontbtn
import com.example.banplus.viewmodel.CommerceViewModel

@Composable
fun RespTransaction(iData: iTransaction, onclickimprimir:(Commerce) ->Unit, onClickMainActivity: () -> Unit, status: Boolean ) {
    Scaffold() {
        val context = LocalContext.current
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderInit(icon = R.drawable.ic_recurso_4 )
            BodyContentA(onClick = {onClickMainActivity()}, iData =iData, onclickimprimir = {onclickimprimir(it)}, status = status )
        }
    }
}
@Composable
private fun BodyContentA(onClick: () -> Unit, iData: iTransaction, onclickimprimir: (Commerce) -> Unit, status: Boolean, viewModel: CommerceViewModel = hiltViewModel(), ) {
    val commerce = viewModel.commerce.observeAsState(Commerce("", "","",""))

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 9.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        cardsAlert(rif = true, title = stringResource(id = R.string.recibo_operacion), status = status, iData = iData )
        Column(
            modifier = Modifier.align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            BtnNext(
                text = stringResource(id = R.string.imprimir),
                onClick = { onclickimprimir(commerce.value) },
                ico = painterResource(id = R.drawable.ic_next),
                modifier = Modifier
                    .padding(4.dp)
                    .height(49.dp)
                    .width(240.dp)
            )
            BtnNext(
                text = stringResource(id = R.string.finalizar),
                onClick =  {onClick()},
                ico = painterResource(id = R.drawable.ic_next),
                background= color_fontbtn,
                modifier = Modifier
                    .padding(4.dp)
                    .height(49.dp)
                    .width(240.dp)
            )

        }
    }
}