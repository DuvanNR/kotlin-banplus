package com.example.banplus.template

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.banplus.Activity.BancosList
import com.example.banplus.Activity.ListTypeDocument
import com.example.banplus.R
import com.example.banplus._interface.idropdown
import com.example.banplus.component.DropdownDemo
import com.example.banplus.component.PostField
import com.example.banplus.component.inputNumber
import com.example.banplus.component.template.CardComponent
import com.example.banplus.component.template.FormBtns
import com.example.banplus.component.template.LayoutTemplate
import com.example.banplus.ui.theme.color_black
import com.example.banplus.ui.theme.color_fontbtn
import com.example.banplus.utils.addDecimals
import com.example.banplus.utils.mobileNumberFilter
import java.util.*

data class FormData(
    var telefono: String = "",
    var typeDocument: idropdown = idropdown(key = "V", title = "V"),
    var numDocument: String = "",
    var monto: String = "",
    var banco: idropdown = idropdown(
        key = "0174",
        title = "Banplus, C.A."
    ),
    var ref: String = "",
    var message: String = ""
)

@Composable
fun VueltoTemplate() {
    LayoutTemplate(iconBoolean = false, children = { Body() })
}

@Composable
private fun Body() {
    var (form, setForm) = remember { mutableStateOf<FormData>(FormData()) }
    var formActive by remember { mutableStateOf<Int>(0) }
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = if (formActive != 2) 44.dp else 0.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            when (formActive) {
                0 -> {
                    BodyContent(form, setForm)
                }
                1 -> {
                    Form2Next(form, setForm)
                }
                2 -> {
                    ConfirmateTransaccion(form)
                }

            }
        }

        Column(modifier = Modifier.align(Alignment.BottomCenter)) {
            FormBtns(
                numTab = formActive,
                setNumTab = {
                    formActive = it
                }, form
            )
        }

    }

}


@Composable
private fun BodyContent(data: FormData, setData: (FormData) -> Unit) {
    Column(modifier = Modifier) {
        Row() {
            DropdownDemo(
                Modifier
                    .size(height = 65.dp, width = 120.dp)
                    .padding(end = 3.dp),
                textColor = Color.Black,
                selectedOptionText = data.typeDocument,
                onValueChange = {
                    setData(data.copy(typeDocument = it))
                },
                modifierDropdownItem = Modifier.border(
                    BorderStroke(
                        0.5.dp,
                        Color.Gray
                    )
                ),
                label = stringResource(id = R.string.tipo),
                options = ListTypeDocument

            )
            PostField(
                text = data.numDocument,
                onValueChange = {
                    setData(data.copy(numDocument = if (it.length > 9 || it.any { !it.isDigit() }) data.numDocument else it))
                },
                label = stringResource(id = R.string.cedula),
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.NumberPassword,
                    imeAction = ImeAction.Next
                )
            )
        }
        PostField(
            text = data.telefono,
            onValueChange = {
                setData(data.copy(telefono = if (it.length > 11 || it.any { !it.isDigit() }) data.telefono else it))
            },
            label = stringResource(id = R.string.telefono),
            visualTransformation = {
                mobileNumberFilter(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.NumberPassword, imeAction = ImeAction.Next
            ),
        )
    }


}

@Composable
private fun Form2Next(data: FormData, setData: (FormData) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        DropdownDemo(
            modifier = Modifier.fillMaxWidth(),
            selectedOptionText = data.banco, onValueChange = {
                setData(
                    data.copy(
                        banco = it
                    )
                )
            },
            textColor = Color.Black,
            modifierDropdownItem = Modifier.border(BorderStroke(0.5.dp, Color.Gray)),
            label = stringResource(id = R.string.banco).capitalize(),
            options = BancosList

        )
        inputNumber(value = data.monto, onNumberChange = {
            setData(
                data.copy(
                    monto = if (it.startsWith("0")) {
                        ""
                    } else {
                        if (it.length > 10) data.monto else it
                    }
                )
            )
        })
    }
}

@Composable
private fun ConfirmateTransaccion(data: FormData) {
    CardComponent(children = {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 34.dp)

        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 88.dp)
            ) {
                Text(
                    text = "${stringResource(id = R.string.cedula)}: ",
                    color = color_fontbtn,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "${data.typeDocument.key}-${data.numDocument}",
                    color = color_fontbtn
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "${stringResource(id = R.string.telefono)}: ",
                    color = color_fontbtn,
                    fontWeight = FontWeight.Bold
                )
                Text(text = data.telefono, color = color_fontbtn)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "${stringResource(id = R.string.banco)}:  ",
                    color = color_fontbtn,
                    fontWeight = FontWeight.Bold
                )
                Text(text = "${data.banco.title}", color = color_fontbtn)
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = R.string.monto),
                    fontSize = 20.sp,
                    color = color_black,
                    fontWeight = FontWeight.Bold
                )
                val amountConverte = addDecimals(data.monto).toDouble()
                Text(
                    text = "Bs. ${"%,.2f".format(Locale.GERMAN, amountConverte)}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 28.sp,
                    color = color_black
                )
            }
        }
    }, title = "Confirmacion de transaccion")

}
//@Composable
//fun Greeting(name: String) {
//    Text(text = "Hello $name!")
//}
//
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview2() {
//    BanplusTheme {
//        Greeting("Android")
//    }
//}