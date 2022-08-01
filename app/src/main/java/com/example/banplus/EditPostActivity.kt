package com.example.banplus

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.example.banplus._interface.idropdown
import com.example.banplus.component.BtnNext
import com.example.banplus.component.DropdownDemo
import com.example.banplus.component.LoadingWheel
import com.example.banplus.component.PostField
import com.example.banplus.component.header.HeaderInit
import com.example.banplus.db.schema.Commerce
import com.example.banplus.ui.theme.BanplusTheme
import com.example.banplus.viewmodel.CommerceViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditPostActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val InitActivity = Intent(this,MainActivity::class.java )
        super.onCreate(savedInstanceState)
        setContent {
            BanplusTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Top,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            HeaderInit()
                            ViewInitBody(intent = {
                                startActivity(InitActivity)
                                finish()

                            })
                        }
                }
            }
        }
    }
}


@Composable
private fun ViewInitBody(viewModel: CommerceViewModel = hiltViewModel(), intent: ()-> Unit) {
    val commerces by viewModel.commerce.observeAsState(Commerce("", "","",""))
    val isLoadding by viewModel.isLoading.observeAsState(false)

    println("vallidate =====================${commerces.razonSocial == ""}")
    if(commerces.razonSocial == "") {
        LoadingWheel()
    }else {
        var razonSocial by remember { mutableStateOf(commerces.razonSocial) }
        var telefono by remember { mutableStateOf(commerces.telefono) }
        var rif by remember { mutableStateOf(commerces.rif) }
        var tipo by remember { mutableStateOf(idropdown(commerces.tipo, commerces.tipo)) }
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
                        razonSocial = it
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
                        telefono = if (it.length > 12 || it.any { !it.isDigit() }) telefono else it
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
                            .size(height = 65.dp, width = 89.dp)
                            .padding(end = 2.3.dp),
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
                        viewModel.updateCommerce(
                            Commerce(
                                razonSocial = razonSocial,
                                rif = rif,
                                telefono = telefono,
                                tipo = "${tipo.key}",
                                id = commerces.id
                            )
                        )
                        Toast.makeText(context, "Terminal Actualizado Exitosamente",
                            Toast.LENGTH_SHORT
                        ).show()
                        intent()
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

}

