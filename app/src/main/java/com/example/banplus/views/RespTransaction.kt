package com.example.banplus.views

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.banplus.R
import com.example.banplus._interface.iTransaction
import com.example.banplus.api.vuelto.response.Tranferp2pResponse
import com.example.banplus.component.BtnNext
import com.example.banplus.component.cardsAlert
import com.example.banplus.component.header.HeaderInit
import com.example.banplus.ui.theme.color_fontbtn

@Composable
fun RespTransaction(iData: iTransaction, onClickMainActivity: () -> Unit) {
    Scaffold() {
        val context = LocalContext.current
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderInit(icon = R.drawable.ic_recurso_4 )
            BodyContentA(onClick = {onClickMainActivity()}, iData =iData )
        }
    }
}
@Composable
private fun BodyContentA(onClick: () -> Unit, iData: iTransaction) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top =44.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        cardsAlert(rif = true, title = "Recibo Operación", status = true, iData = iData )
        Column(
            modifier = Modifier.align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            BtnNext(
                text = "Imprimir",
                onClick = {  },
                ico = painterResource(id = R.drawable.ic_next),
                modifier = Modifier
                    .padding(4.dp)
                    .height(49.dp)
                    .width(240.dp)
            )
            BtnNext(
                text = "Finalizar",
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