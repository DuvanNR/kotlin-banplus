package com.example.banplus.views

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.banplus.R
import com.example.banplus.component.BtnNext
import com.example.banplus.component.PostField
import com.example.banplus.component.header.HeaderInit

@Composable
fun vueltoNextForm(navigate: NavController, cell: String?, cedula:String?, tipo: String?) {
    Scaffold() {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally) {
            HeaderInit()
            nextVueltoBody(cell,cedula,tipo)
        }
    }
}


@Composable
fun nextVueltoBody( tipo: String?, cedula:String?, cell: String?) {
    var banco by remember { mutableStateOf("") }
    var monto by remember { mutableStateOf("0.0") }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal=44.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            PostField(text = banco, onValueChange = {banco = it}, label="Banco", modifier = Modifier.fillMaxWidth())
            PostField(text = monto, onValueChange = {monto = it}, label="Monto", modifier = Modifier.fillMaxWidth())
        }
        BtnNext(
            text = "Guardar",
            onClick = { onClick(banco, monto) },
            ico = painterResource(id = R.drawable.ic_next),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(4.dp)
                .height(49.dp)
                .width(240.dp)
        )

    }
}
fun onClick(a:String, b:String) {
    println("$a..........$b")
}