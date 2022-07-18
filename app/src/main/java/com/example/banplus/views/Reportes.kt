package com.example.banplus.views

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.banplus.R
import com.example.banplus.component.BtnNext
import com.example.banplus.component.cardsAlert
import com.example.banplus.component.header.HeaderInit
import com.example.banplus.navigation.PathRouter
import com.example.banplus.ui.theme.*

@Composable
fun ViewReportes(navController: NavController) {
    Scaffold() {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally) {
            HeaderInit(icon = R.drawable.ic_recurso_4)
            ReportesBody(onClickListRe = {
                navController.navigate(PathRouter.ListReport.route)
            })
        }

    }

}

@Composable
fun ReportesBody(onClickListRe:()-> Unit) {
    Box(modifier = Modifier
        .fillMaxSize(),
        ) {
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 22.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center){
            cardsAlert()

         }

        Column(
            modifier = Modifier.align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
            ) {

            BtnNext(
                text = "Imprimir",
                onClick = { },
                ico = painterResource(id = R.drawable.ic_next),
                modifier = Modifier
                    .padding(4.dp)
                    .height(49.dp)
                    .width(240.dp)
            )
            BtnNext(
                text = "Ver Detalles",
                onClick =  onClickListRe,
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