package com.example.banplus.component.template

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.banplus.component.DialogBoxLoading
import com.example.banplus.component.header.HeaderInit
import com.example.banplus.context.LoadingContext
import com.example.banplus.context.LoginContext
import com.example.banplus.template.LoginTemplate
import com.example.banplus.ui.theme.BanplusTheme

@SuppressLint("UnrememberedMutableState")
@Composable
fun LayoutTemplate(
    iconBoolean: Boolean = true,
    MenuBoolean: Boolean = false,
    children: @Composable () -> Unit,
) {
    val context = LocalContext.current
    val isLoading by mutableStateOf(LoadingContext.isLoading.value)
    val isLogin by mutableStateOf(LoginContext.isLogin.value)
    BanplusTheme {
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
        ) {
            if (isLoading) {
                LoadingContext.endproces()
                DialogBoxLoading()
            }
            if (isLogin) {
                LoginTemplate()
            } else {

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top,
                ) {
                    HeaderInit(iconBoolean = iconBoolean, menu = MenuBoolean)
                    children()
                }
            }

        }
    }
}
//
//@Composable
//private fun Body() {
//    Text(text = "hola Mundo")
//}
//
//
//@Preview(showBackground = true, widthDp = 300, heightDp = 400)
//@Composable
//fun DefaultPreview() {
//    LayoutTemplate(
//        children = { Body() },
//    )
//}