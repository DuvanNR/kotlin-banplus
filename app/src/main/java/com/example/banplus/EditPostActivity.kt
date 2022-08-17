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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.banplus._interface.idropdown
import com.example.banplus.api.ApiResponseStatus
import com.example.banplus.api.backend.dto.LoginDTO
import com.example.banplus.component.*
import com.example.banplus.component.header.HeaderInit
import com.example.banplus.db.schema.Commerce
import com.example.banplus.inject_dependency.HiltInjectApp
import com.example.banplus.ui.theme.BanplusTheme
import com.example.banplus.utils.ConverString
import com.example.banplus.utils.mobileNumberFilter
import com.example.banplus.viewmodel.BackendViewModel
import com.example.banplus.viewmodel.CommerceViewModel
import com.nexgo.oaf.apiv3.DeviceEngine
import com.nexgo.oaf.apiv3.DeviceInfo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditPostActivity : ComponentActivity() {
    private var deviceEngine: DeviceEngine? = null
    private var deviceInfo: DeviceInfo? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        val InitActivity = Intent(this, MainActivity::class.java)
        super.onCreate(savedInstanceState)
        setContent {
            deviceEngine = (application as HiltInjectApp).deviceEngine
            deviceInfo = deviceEngine!!.deviceInfo
            BanplusTheme {
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
                        BodyComponent(
                            intent = {
                                startActivity(InitActivity)
                                finish()
                            },
                            serial = "${deviceInfo!!.sn}"
                        )

                    }
                }
            }
        }
    }
}

@Composable
private fun BodyComponent(
    intent: () -> Unit, viewModel: CommerceViewModel = hiltViewModel(),
    backendViewModel: BackendViewModel = hiltViewModel(),
    serial: String
) {
    val commerces by viewModel.commerce.observeAsState(Commerce("", "", "", ""))
    val loadding by viewModel.isLoading.observeAsState(true)
    val status = backendViewModel.status.value
    if (status is ApiResponseStatus.Loading) {
        LoadingWheel()
    } else if (status is ApiResponseStatus.Error) {
        errorDialog(description = status.messageId, onDialogDismiss = {
            backendViewModel.status.value = null
        })
    }
    if (commerces.tipo == "") {
        LoadingWheel()
        backendViewModel.login(LoginDTO(username = stringResource(id = R.string.USER_BACKEND), password = stringResource(
            id = R.string.PASS_BACKEND
        )))
    } else {
        ViewInitBody(
            intent = intent,
            viewModel = viewModel,
            backendViewModel = backendViewModel,
            serial = serial,
            commerces = commerces
        )
    }
}


@Composable
private fun ViewInitBody(
    viewModel: CommerceViewModel,
    backendViewModel: BackendViewModel,
    commerces: Commerce,
    intent: () -> Unit,
    serial: String
) {
    val login = backendViewModel.InfoTerminal.value
    var serial by remember { mutableStateOf(serial) }
    var razonSocial by remember { mutableStateOf(commerces.razonSocial) }
    var telefono by remember { mutableStateOf(commerces.telefono) }
    var rif by remember { mutableStateOf(commerces.rif) }
    var tipo by remember { mutableStateOf(idropdown(commerces.tipo, commerces.tipo)) }
    var btnStatus by remember { mutableStateOf(true) }
    val context = LocalContext.current

    if (login.rif !== null) {
        razonSocial = "${login.nameCommerce}"
        telefono = "${login.telefono}"
        rif = "${login.cedula}"
        tipo = idropdown("${login.typeCedula}", "${login.typeCedula}")
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 44.dp),
        contentAlignment = Alignment.TopCenter
    ) {

        Column(modifier = Modifier.padding(top = 8.dp)) {
            PostField(
                text = serial,
                onValueChange = {},
                readOnly = false,
                label = stringResource(id = R.string.serial),
                modifier = Modifier.fillMaxWidth(),
            )

            PostField(
                text = razonSocial,
                onValueChange = {},
                label = stringResource(id = R.string.razon_social),
                modifier = Modifier.fillMaxWidth(),
                readOnly = false,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
            )
            PostField(
                text = ConverString("${telefono}",4,3),
                onValueChange = {},
                label = stringResource(id = R.string.telefono),
                modifier = Modifier.fillMaxWidth(),
                readOnly = false,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.NumberPassword,
                    imeAction = ImeAction.Next
                ),
                visualTransformation = {
                    mobileNumberFilter(it)
                },
            )

            Row(modifier = Modifier.fillMaxWidth()) {
                PostField(
                    modifier = Modifier
                        .size(height = 65.dp, width = 89.dp)
                        .padding(end = 2.3.dp),
                    text = "${tipo.key}",
                    onValueChange = {  },
                    label = stringResource(id = R.string.tipo),
                    readOnly = false,

                    )
                PostField(
                    text = rif,
                    onValueChange = {
                    },
                    label = stringResource(id = R.string.rif),
                    modifier = Modifier.fillMaxWidth(),
                    readOnly = false,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.NumberPassword,
                    ),

                    )
            }

        }

        if (btnStatus) {
            BtnNext(
                text = stringResource(id = R.string.sincronizar),
                onClick = {
                    btnStatus = !btnStatus
                    backendViewModel.getSerial(serial)
                    Toast.makeText(
                        context, "Sincronizando los datos",
                        Toast.LENGTH_SHORT
                    ).show()
                },
                ico = painterResource(id = R.drawable.ic_next),
                enabled = btnStatus,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(4.dp)
                    .height(49.dp)
                    .width(240.dp)
            )
        } else {
            BtnNext(
                text = stringResource(id = R.string.guardar),
                enabled = !btnStatus,
                onClick = {
                    if (razonSocial != "" && telefono != "" && rif != "") {
                        if (telefono.length == 11) {
                            viewModel.updateCommerce(
                                Commerce(
                                    razonSocial = razonSocial,
                                    rif = rif,
                                    telefono = telefono,
                                    tipo = "${tipo.key}",
                                    id = commerces.id
                                )
                            )
                            Toast.makeText(
                                context, "Terminal Actualizado Exitosamente",
                                Toast.LENGTH_SHORT
                            ).show()
                            intent()
                        } else {
                            Toast.makeText(
                                context, R.string.validTelefono,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {

                        Toast.makeText(
                            context, "Debes llenar todos los campos",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
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

}

