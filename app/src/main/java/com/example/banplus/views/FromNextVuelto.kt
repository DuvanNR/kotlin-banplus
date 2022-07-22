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
import com.example.banplus.BancosList
import com.example.banplus.R
import com.example.banplus._interface.iTransaction
import com.example.banplus._interface.idropdown
import com.example.banplus.component.*
import com.example.banplus.component.header.HeaderInit
import com.example.banplus.navigation.PathRouter

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
            HeaderInit(icon = R.drawable.ic_recurso_4)
            nextVueltoBody(onClick = {
                if (it.monto != "" || it.banco != "") {
                    navigate.navigate(
                        PathRouter.ConfirTrancation.withArgs(
                            "${resp.tipo}",
                            "${resp.cedula}",
                            "${resp.telefono}",
                            "${it.banco}",
                            "${it.monto}",
                            "${it.nameBanco}"
                        )
                    )
                } else {
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
fun nextVueltoBody(onClick: (iTransaction) -> Unit) {
    var banco by remember {
        mutableStateOf<idropdown>(
            idropdown(
                key = "0174",
                title = "Banplus, C.A."
            )
        )
    }
    var monto by remember { mutableStateOf("11") }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 44.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 33.dp),

            ) {

            DropdownDemo(
                modifier = Modifier.fillMaxWidth(),
                selectedOptionText = banco, onValueChange = { banco = it },
                label = "Banco",
                options = BancosList

            )
            PostField(
                text = monto,
                onValueChange = { monto = it },
                label = "Monto",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
            )
        }
        BtnNext(
            text = "Guardar",
            onClick = {
                onClick(
                    iTransaction(
                        banco = "${banco.key}",
                        monto = "$monto.00",
                        nameBanco = "${banco.title}",
                        cedula = "",
                        tipo = "",
                        telefono = ""
                    )
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
