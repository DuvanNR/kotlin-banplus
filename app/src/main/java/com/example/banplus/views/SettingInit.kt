package com.example.banplus.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.banplus.ListTypeRif
import com.example.banplus.R
import com.example.banplus._interface.idropdown
import com.example.banplus.api.ApiResponseStatus
import com.example.banplus.api.backend.dto.LoginDTO
import com.example.banplus.component.*
import com.example.banplus.component.header.HeaderInit
import com.example.banplus.db.schema.Commerce
import com.example.banplus.utils.ConverString
import com.example.banplus.utils.checkForInternet
import com.example.banplus.utils.mobileNumberFilter
import com.example.banplus.viewmodel.BackendViewModel
import com.example.banplus.viewmodel.CommerceViewModel
import java.util.*
import kotlin.concurrent.timerTask

@Composable
fun SettingInitView(
    viewModel: CommerceViewModel = hiltViewModel(),
    backendViewModel: BackendViewModel = hiltViewModel(),
    onRedirection: () -> Unit,
    serial: String
) {
    val commerces by viewModel.commerces.observeAsState(arrayListOf())
    val isLoaddig by viewModel.isLoading.observeAsState(false)
    if (commerces.isNotEmpty()) {
        onRedirection()
    } else {
        Scaffold {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                HeaderInit()
                ViewInitBody(viewModel, serial = serial, backendViewModel = backendViewModel)
            }
        }

    }

    val status = backendViewModel.status.value
    if (status is ApiResponseStatus.Loading) {
        LoadingWheel()
    } else if (status is ApiResponseStatus.Error) {
        println("Error__________${status.messageId}")
        errorDialog(description = status.messageId, onDialogDismiss = {
            backendViewModel.status.value = null
        })
    }

}


@Composable
private fun ViewInitBody(
    viewModel: CommerceViewModel,
    serial: String,
    backendViewModel: BackendViewModel
) {

    var btnStatus by remember { mutableStateOf(true) }
    var getAlarte by remember { mutableStateOf(false) }
    if (getAlarte) {
        errorDialog(description = R.string.SINCONEXION, onDialogDismiss = {
            getAlarte = false
        })
    }
    val context = LocalContext.current
    var token = backendViewModel.resp.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 44.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        if (backendViewModel.InfoTerminal.value.rif !== null) {
            val aa = backendViewModel.InfoTerminal.value
            var razonSocial by remember { mutableStateOf(aa.nameCommerce) }
            var telefono by remember { mutableStateOf(aa.telefono) }
            var rif by remember { mutableStateOf(aa.cedula) }
            var tipo by remember { mutableStateOf(idropdown(aa.typeCedula, aa.typeCedula)) }
            Column(modifier = Modifier.padding(top = 8.dp)) {
                PostField(
                    text = "$razonSocial",
                    onValueChange = {
                    },
                    label = stringResource(id = R.string.razon_social),
                    readOnly = false,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                )
                PostField(
                    text = ConverString("${telefono}",4,3),
                    onValueChange = {
                    },
                    readOnly = false,
                    label = stringResource(id = R.string.telefono),
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.NumberPassword,
                        imeAction = ImeAction.Next
                    ),
                    visualTransformation = {
                        mobileNumberFilter(it)
                    },


                    )

                Row(modifier = Modifier.fillMaxWidth()) {
                    DropdownDemo(
                        Modifier
                            .size(height = 65.dp, width = 89.dp)
                            .padding(end = 2.3.dp),
                        selectedOptionText = tipo, onValueChange = {  },
                        label = stringResource(id = R.string.tipo),
                        options = ListTypeRif

                    )
                    PostField(
                        text = "$rif",
                        readOnly = false,
                        onValueChange = {
                        },
                        label = stringResource(id = R.string.rif),
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.NumberPassword,
                            imeAction = ImeAction.Next
                        ),
                    )
                }

            }
            BtnNext(
                text = stringResource(id = R.string.guardar),
                onClick = {
                    val aa = checkForInternet(context)
                    if (aa) {
                        btnStatus = true
                        viewModel.addCommerce(
                            Commerce(
                                razonSocial = "$razonSocial",
                                rif = "$rif",
                                telefono = "$telefono",
                                tipo = "${tipo.key}"
                            )
                        )
                    } else {
                        getAlarte = true

                    }

                },
                enabled = !btnStatus,
                ico = painterResource(id = R.drawable.ic_next),
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(4.dp)
                    .height(49.dp)
                    .width(240.dp)
            )
        } else {
            Text(
                text = stringResource(id = R.string.config_terminal),
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 20.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Text(
                text = "Serial: $serial",
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 70.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )

            val vv = stringResource(id = R.string.USER_BACKEND )
            val bb = stringResource(id = R.string.PASS_BACKEND )
            BtnNext(
                text = stringResource(id = R.string.sincronizar),
                onClick = {
                    val aa = checkForInternet(context)
                    if (aa) {
                        btnStatus = false
                        backendViewModel.login(
                            LoginDTO(
                                vv,
                                bb
                            )
                        )
                        Timer().schedule(timerTask {
                            backendViewModel.getSerial("${serial}")
                        }, 2000)
                    } else {
                        getAlarte = true
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
