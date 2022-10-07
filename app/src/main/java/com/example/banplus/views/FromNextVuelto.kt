package com.example.banplus.views

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.banplus.Activity.BancosList
import com.example.banplus.R
import com.example.banplus._interface.iTransaction
import com.example.banplus._interface.idropdown
import com.example.banplus.component.*
import com.example.banplus.component.header.HeaderInit
import com.example.banplus.navigation.PathRouter
import com.example.banplus.utils.addDecimals

@Composable
fun vueltoNextForm(
    navigate: NavController,
    resp: iTransaction
) {
    Scaffold() {
        val context = LocalContext.current
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderInit(
                iconBoolean = false,
                menu = false,
            )
            nextVueltoBody(onClick = {
                println(it.monto)
                if (it.monto != "" && it.banco != "" && it.monto != "0") {
                    navigate.navigate(
                        PathRouter.ConfirTrancation.withArgs(
                            "${resp.tipo}",
                            "${resp.cedula}",
                            "${resp.telefono}",
                            "${it.banco}",
                            "${addDecimals(it.monto)}",
                            "${it.nameBanco}"
                        )
                    )
                } else {
                    Toast.makeText(
                        context,
                        R.string.error_monto_min,
                        Toast.LENGTH_SHORT
                    ).show()

                }
            })
        }
    }


}


@Composable
fun nextVueltoBody(onClick: (iTransaction) -> Unit) {
    var banco by remember {
        mutableStateOf<idropdown>(
            idropdown(
                key = "0174",
                title = "Banplus, C.A."
            )
        )
    }
    var monto by remember { mutableStateOf("") }

//    val formatter = DecimalFormat("#,###.00")

}
