package com.example.banplus.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.banplus.R
import com.example.banplus.component.BtnIni

@Composable
fun ViewInit() {
    Scaffold() {
        ViewInitBody()
    }
}

@Composable
fun ViewInitBody() {
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BtnIni(ico = painterResource(id = R.drawable.ic_time)  , onClick = {/*TODO: */})
        BtnIni(ico = painterResource(id = R.drawable.ic_lists)  , onClick = {/*TODO: */})
    }
}

