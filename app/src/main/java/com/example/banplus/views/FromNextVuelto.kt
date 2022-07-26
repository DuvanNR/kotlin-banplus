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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavController
import com.example.banplus.BancosList
import com.example.banplus.R
import com.example.banplus._interface.iTransaction
import com.example.banplus._interface.idropdown
import com.example.banplus.component.*
import com.example.banplus.component.header.HeaderInit
import com.example.banplus.navigation.PathRouter
import com.example.banplus.utils.addDecimals
import com.example.banplus.utils.mobileNumberFilter
import java.text.DecimalFormat

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
                        "El monto tiene que se mayor a 0.01",
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

//    val formatter = DecimalFormat("#,###.00")
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
            inputNumber(value = monto, onNumberChange = {
                if (it.startsWith("0")) {
                  monto =  ""
                } else {
                   monto = if (it.length > 11) monto else it
                }
            })
        }
        BtnNext(
            text = "Siguiente",
            onClick = {
                onClick(
                    iTransaction(
                        banco = "${banco.key}",
                        monto = "${monto}",
                        nameBanco = "${banco.title}",
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
