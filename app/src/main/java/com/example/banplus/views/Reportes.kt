package com.example.banplus.views

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

@Composable
fun ViewReportes(navController: NavController) {
    Scaffold() {
        ReportesBody()
    }

}

@Composable
fun ReportesBody( ) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(text = "Hola Estatus")
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Navergar")

        }

    }
}

