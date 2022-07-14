package com.example.banplus.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.banplus.R
import com.example.banplus.component.BtnIni
import com.example.banplus.component.header.HeaderInit
import com.example.banplus.navigation.PathRouter

@Composable
fun ViewInit(navController: NavController) {
    Scaffold {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally) {
            HeaderInit()
            Text(
                modifier = Modifier.padding(bottom = 117.dp),
                fontSize = 24.sp,
                text = "Bienvenido")
            ViewInitBody(navController)
        }

    }
}

@Composable
fun ViewInitBody(navController: NavController) {
    Box() {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            BtnIni(
                text="Vuelto",
                ico = painterResource(id = R.drawable.ic_vuelto)  , onClick = {navController.navigate(PathRouter.VueltoRoute.route)})
            BtnIni(
                text="Reportes",
                ico = painterResource(id = R.drawable.ic_reportes)  , onClick = {navController.navigate(PathRouter.ReporteRoute.route)})
        }
    }

}

