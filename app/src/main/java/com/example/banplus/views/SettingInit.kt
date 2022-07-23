package com.example.banplus.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.banplus.R
import com.example.banplus.component.BtnIni
import com.example.banplus.component.BtnNext
import com.example.banplus.component.PostField
import com.example.banplus.component.header.HeaderInit
import com.example.banplus.navigation.PathRouter

@Composable
fun SettingInitView () {
    Scaffold {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally) {
            HeaderInit()
            ViewInitBody()
        }

    }

}


@Composable
private fun ViewInitBody() {
    var razonSocial by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var rif by remember { mutableStateOf("") }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 44.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.padding(top=8.dp)) {
                PostField(
                    text = razonSocial,
                    onValueChange = {razonSocial = if (it.length > 111 || it.any { !it.isDigit() }) razonSocial else it},
                    label = "Razón Social",
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next),


                )
                PostField(
                    text = telefono,
                    onValueChange = {telefono = if (it.length > 111 || it.any { !it.isDigit() }) telefono else it},
                    label = "Telefono",
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next),


                    )
                PostField(
                    text = rif,
                    onValueChange = {rif = if (it.length > 111 || it.any { !it.isDigit() }) rif else it},
                    label = "Rif",
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next),
                    )

            }




        }
        BtnNext(
            text = "Guardar",
            onClick = {  },
            ico = painterResource(id = R.drawable.ic_next),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(4.dp)
                .height(49.dp)
                .width(240.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSettingInit() {
SettingInitView()
}