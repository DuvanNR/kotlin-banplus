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
            Text(
                modifier = Modifier.padding(bottom = 117.dp),
                fontSize = 24.sp,
                text = "Bienvenido")
            ViewInitBody()
        }

    }

}


@Composable
private fun ViewInitBody() {
    var RazonSocial by remember { mutableStateOf("") }
    var Rif by remember { mutableStateOf("") }

    Box() {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.padding(top=33.dp)) {
                PostField(
                    text = RazonSocial,
                    onValueChange = {RazonSocial = if (it.length > 111 || it.any { !it.isDigit() }) RazonSocial else it},
                    label = "Razón Social",
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next),


                )


            }

        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSettingInit() {
SettingInitView()
}