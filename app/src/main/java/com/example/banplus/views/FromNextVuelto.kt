package com.example.banplus.views

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
import com.example.banplus.navigation.PathRouter

@Composable
fun vueltoNextForm(
    navigate: NavController,
    tipo: String?,
    cedula: String?,
    cell: String?,

) {
    Scaffold() {
        val context = LocalContext.current
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderInit(icon = R.drawable.ic_recurso_4)
            nextVueltoBody(cell, cedula, tipo,  onClick = { tipo, cedula,cell, banco, monto ->
                if(monto!="" || banco != "") {
                    navigate.navigate(PathRouter.ConfirTrancation.withArgs(tipo , cedula , cell, banco, monto))
                }else{
                    Toast.makeText(
                        context,
                        "Debes llenar todos los campos",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            })
        }
    }

}


@Composable
fun nextVueltoBody(
    tipo: String?, cedula: String?, cell: String?, onClick: (String,String,String,String,String) -> Unit
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
                onValueChange = {  banco = if (it.length > 30 || it.any { !it.isDigit() }) banco else it },
                label = "Banco",
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next)
            )
            PostField(
                text = monto,
                onValueChange = {monto = if (it.length > 30 || it.any { !it.isDigit() }) monto else it},
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
                onClick("$tipo", "$cedula", "$cell", banco, monto)
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
