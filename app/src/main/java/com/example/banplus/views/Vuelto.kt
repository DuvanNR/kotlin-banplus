package com.example.banplus.views

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
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
import com.example.banplus.component.BtnNext
import com.example.banplus.component.DropdownDemo
import com.example.banplus.component.PostField
import com.example.banplus.component.header.HeaderInit
import com.example.banplus.navigation.PathRouter
import com.example.banplus.utils.isNumber
import com.example.banplus.utils.mobileNumberFilter

@Composable
fun ViewVuelto(navController: NavController) {
    val context = LocalContext.current

    Scaffold() {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderInit()
            BodyContent { tipo, celular, cell ->
                if (tipo != "" && celular != "" && cell != "") {
                    navController.navigate(PathRouter.VueltoNextRoute.withArgs(tipo, cell, celular))

                }else {
                    Toast.makeText(
                        context,
                        "Debes llenar todos los campos",
                        Toast.LENGTH_SHORT
                    ).show()

                }

            }
        }
    }
}

@Composable
fun BodyContent(onClick: (tipo: String, celular: String, cell: String) -> Unit) {
    var tipo by remember { mutableStateOf("v") }
    var cedula by remember { mutableStateOf("7950354") }
    var cell by remember { mutableStateOf("584241127426") }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 44.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column() {
            Row() {
                DropdownDemo(
                    Modifier
                        .size(height = 70.dp, width = 120.dp)
                        .padding(end = 30.dp),
                    selectedOptionText = tipo, onValueChange = { tipo = it },
                    label = "Tipo",
                )
                PostField(
                    text = cedula,
                    onValueChange = {cedula = if (it.length > 3 || it.any { !it.isDigit() }) cedula else it},
                    label = "Cedula",
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next)
                )
            }
            PostField(
                text = cell,
                onValueChange = {cell = if (it.length > 3 || it.any { !it.isDigit() }) cell else it},
                label = "Telefono",
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next),
            )
        }

        BtnNext(
            text = "Siguiente",
            onClick = { onClick(tipo, cedula, cell) },
            ico = painterResource(id = R.drawable.ic_next),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(4.dp)
                .height(49.dp)
                .width(240.dp)
        )

    }
}
