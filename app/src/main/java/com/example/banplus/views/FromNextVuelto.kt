package com.example.banplus.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.banplus.R
import com.example.banplus.api.ApiResponseStatus
import com.example.banplus.api.vuelto.response.Tranferp2pResponse
import com.example.banplus.component.BtnNext
import com.example.banplus.component.LoadingWheel
import com.example.banplus.component.PostField
import com.example.banplus.component.errorDialog
import com.example.banplus.component.header.HeaderInit

@Composable
fun vueltoNextForm(
    navigate: NavController,
    cell: String?,
    cedula: String?,
    tipo: String?,
    status: ApiResponseStatus<Tranferp2pResponse.Pago>? = null,
    onClick: (
        cedula: String,
        cell: String,
        banco: String,
        tipo: String,
        monto: String
    ) -> Unit,
) {
    Scaffold() {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderInit()
            nextVueltoBody(cell, cedula, tipo, onClick = onClick)
        }
    }
    if (status is ApiResponseStatus.Loading) {
        LoadingWheel()
    }else if( status is ApiResponseStatus.Error ) {
        errorDialog(status = status, onDialogDismiss = {})
    }
}


@Composable
fun nextVueltoBody(
    tipo: String?, cedula: String?, cell: String?, onClick: (
        cedula: String,
        cell: String,
        banco: String,
        tipo: String,
        monto: String
    ) -> Unit
) {
    var banco by remember { mutableStateOf("0174") }
    var monto by remember { mutableStateOf("1.15") }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 44.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            PostField(
                text = banco,
                onValueChange = {  banco = if (it.length > 3 || it.any { !it.isDigit() }) banco else it },
                label = "Banco",
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next)
            )
            PostField(
                text = monto,
                onValueChange = {monto = if (it.length > 3 || it.any { !it.isDigit() }) monto else it},
                label = "Monto",
                modifier = Modifier.fillMaxWidth(),

                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next)
            )
        }
        BtnNext(
            text = "Guardar",
            onClick = {
                onSubmint(
                    _tipo = tipo,
                    _cedula = cedula,
                    _cell = cell,
                    _banco = banco,
                    _monto = monto,
                    onClick = onClick
                )
            },
            ico = painterResource(id = R.drawable.ic_next),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(4.dp)
                .height(49.dp)
                .width(240.dp)
        )

    }
}

fun onSubmint(
_tipo: String?, _cedula: String?, _cell: String?, _banco: String, _monto: String, onClick: (
         String,
         String,
         String,
         String,
         String
    ) -> Unit
) {
    print(" $_cedula $_cell , $_banco , $_tipo , $_monto " )
    if(_monto!="" || _banco != "") {
        onClick("$_cedula", "$_cell", "$_banco", "$_tipo" ,_monto  )

    }else {

    }
}