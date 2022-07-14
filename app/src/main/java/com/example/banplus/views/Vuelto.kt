package com.example.banplus.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.banplus.R
import com.example.banplus.component.BtnNext
import com.example.banplus.component.DropdownDemo
import com.example.banplus.component.PostField
import com.example.banplus.component.header.HeaderInit
import com.example.banplus.utils.isNumber

@Composable
fun ViewVuelto(navController: NavController) {


    Scaffold(
        topBar = { },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = { },
        bottomBar = { },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(it)
        ) {
            HeaderInit()
            BodyContent()
        }
    }
}

@Composable
fun BodyContent() {
    var tipo by remember { mutableStateOf("v") }
    var cedula by remember { mutableStateOf("") }
    var cell by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 64.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
        Row(
            modifier = Modifier.padding(horizontal = 44.dp)
        ) {

            // Declaring a boolean value to store
            // the expanded state of the Text Field
            DropdownDemo(selectedOptionText = tipo, onValueChange = {tipo = it} , label = "Tipo" )
            PostField(text = cedula, onValueChange = {cedula = it}, label="Cedula")
        }
        Row(
            modifier = Modifier.padding(horizontal = 44.dp)
        ) {
            PostField(text = cell, onValueChange = {cell = it}, label="Telefono")
        }


    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .height(450.dp)
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        BtnNext(text="Siguiente",onClick = {/* TODO: */}, ico = painterResource(id =  R.drawable.ic_next))

    }
}
