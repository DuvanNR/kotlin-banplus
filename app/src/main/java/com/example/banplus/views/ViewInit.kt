package com.example.banplus.views

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.banplus.R
import com.example.banplus.component.BtnIni
import com.example.banplus.component.header.HeaderInit
import com.example.banplus.viewmodel.VueltoViewModel

@Composable
fun ViewInit( IrEditTerminal: ()-> Unit = {}, checkForInternet:(type:String)->Unit) {
    Scaffold {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally) {
            HeaderInit( menu = true, iconBoolean = true)
            Text(
                modifier = Modifier.padding(bottom = 117.dp),
                fontSize = 24.sp,
                text = stringResource(id = R.string.bienvenido))
            ViewInitBody(checkForInternet={
                checkForInternet(it)
            })
        }

    }
}

@Composable
private fun ViewInitBody(checkForInternet:(type:String)-> Unit, viewModel: VueltoViewModel = hiltViewModel()) {
    Box() {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            BtnIni(
                text= stringResource(id = R.string.pago_plus),
                ico = painterResource(id = R.drawable.ic_btn2)  , onClick = {
                    checkForInternet("vuelto")
                })
            BtnIni(
                text="Reportes",
                ico = painterResource(id = R.drawable.ic_btn1)  , onClick = {
                    viewModel.deleteYesterdayInvoice()
                   checkForInternet("reporte")
                    })
        }

    }

}


