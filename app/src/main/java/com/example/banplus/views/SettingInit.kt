package com.example.banplus.views

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.banplus.ListTypeDocument
import com.example.banplus.ListTypeRif
import com.example.banplus.R
import com.example.banplus._interface.idropdown
import com.example.banplus.api.ApiResponseStatus
import com.example.banplus.component.*
import com.example.banplus.component.header.HeaderInit
import com.example.banplus.db.schema.Commerce
import com.example.banplus.viewmodel.CommerceViewModel

@Composable
fun SettingInitView(viewModel: CommerceViewModel = hiltViewModel(),onRedirection:()->Unit) {
    val commerces by viewModel.commerces.observeAsState(arrayListOf())
    val isLoaddig by viewModel.isLoading.observeAsState(false)
    if(commerces.isNotEmpty()) {
        onRedirection()
    } else {
        Scaffold {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                HeaderInit()
                ViewInitBody(viewModel)
            }
        }

    }

    if (isLoaddig) {
        LoadingWheel()
    }

}


@Composable
private fun ViewInitBody(viewModel: CommerceViewModel) {
    var razonSocial by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var rif by remember { mutableStateOf("") }
    var tipo by remember { mutableStateOf(idropdown("j", "J")) }
    var btnStatus by remember { mutableStateOf(true) }
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 44.dp),
        contentAlignment = Alignment.TopCenter
    ) {

            Column(modifier = Modifier.padding(top = 8.dp)) {
                PostField(
                    text = razonSocial,
                    onValueChange = {
                        razonSocial =
                            if (it.length > 111 || it.any { !it.isLowerCase() }) razonSocial else it
                    },
                    label = "Razón Social",
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),


                    )
                PostField(
                    text = telefono,
                    onValueChange = {
                        telefono = if (it.length > 111 || it.any { !it.isDigit() }) telefono else it
                    },
                    label = "Telefono",
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),


                    )

                Row(modifier = Modifier.fillMaxWidth()) {
                    DropdownDemo(
                        Modifier
                            .size(height = 65.dp, width = 80.dp)
                            .padding(end = 3.dp),
                        selectedOptionText = tipo, onValueChange = { tipo = it },
                        label = "Tipo",
                        options = ListTypeRif

                    )
                    PostField(
                        text = rif,
                        onValueChange = {
                            rif = if (it.length > 111 || it.any { !it.isDigit() }) rif else it
                        },
                        label = "Rif",
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        ),
                    )
                }

            }


        BtnNext(
            text = "Guardar",
            onClick = {
                if(razonSocial != "" && telefono != "" && rif != "" ) {
                    btnStatus = !btnStatus
                    viewModel.addCommerce(
                        Commerce(
                            razonSocial = razonSocial,
                            rif = "${tipo.title}-${rif}",
                            telefono = telefono,
                        )
                    )
                }else {

                    Toast.makeText(context, "Debes llenar todos los campos",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },
            enabled = btnStatus,
            ico = painterResource(id = R.drawable.ic_next),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(4.dp)
                .height(49.dp)
                .width(240.dp)
        )
    }
}
