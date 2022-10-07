package com.example.banplus.template

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.banplus.R
import com.example.banplus._interface.iCommerce
import com.example.banplus.component.BtnNext
import com.example.banplus.component.template.CardComponent
import com.example.banplus.component.template.LayoutTemplate
import com.example.banplus.context.LoginContext.startCardLogin
import com.example.banplus.context.NavTabContext
import com.example.banplus.ui.theme.color_black
import com.example.banplus.ui.theme.color_fontbtn
import com.example.banplus.utils.GoToActivity
import com.example.banplus.utils.getSerialPost
import com.example.banplus.utils.iNameActivity
import com.example.banplus.viewmodel.BackendViewModel

@Composable
fun SettingTemplate() {
    LayoutTemplate(children = { Body() })
}

@SuppressLint("UnrememberedMutableState")
@Composable
private fun Body(
    backendViewModel: BackendViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val infoTerminal by mutableStateOf(backendViewModel.InfoTerminal.value)
    var tabMenu by mutableStateOf(NavTabContext.numTab.value)
    val commerce = iCommerce.getCommerce(context)
    val passwordValue = rememberSaveable { mutableStateOf("") }
    val passwordConfirmationValue = rememberSaveable { mutableStateOf("") }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
        Column(modifier = Modifier.padding(top = 8.dp)) {
            when (tabMenu) {
                0 -> {
                    CardComponent(title = "Configuracion del Pos") {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 87.dp, bottom = 8.dp, start = 25.dp, end = 25.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = "${stringResource(id = R.string.razonSocial)}:  ",
                                    color = color_fontbtn,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = if (infoTerminal.nameCommerce == "") "${commerce?.razonSocial}" else "${infoTerminal.nameCommerce}",
                                    fontSize = 14.sp,
                                    color = color_fontbtn
                                )
                            }
                            Row(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = "${stringResource(id = R.string.rif)}:  ",
                                    color = color_fontbtn,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = if (infoTerminal.cedula == "") "${commerce?.tipo}-${commerce?.rif}" else "${infoTerminal.typeCedula}-${infoTerminal.cedula}",
                                    fontSize = 14.sp,
                                    color = color_fontbtn
                                )
                            }

                            Row(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = "${stringResource(id = R.string.telefono)}:  ",
                                    color = color_fontbtn,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = if (infoTerminal.telefono == "") "${commerce?.telefono}" else "${infoTerminal.telefono}",
                                    fontSize = 14.sp,
                                    color = color_fontbtn
                                )
                            }

//                            Row(
//                                modifier = Modifier.fillMaxWidth()
//                            ) {
//                                Text(
//                                    text = "${stringResource(id = R.string.password)}:  ",
//                                    color = color_fontbtn,
//                                    fontSize = 14.sp,
//                                    fontWeight = FontWeight.Bold
//                                )
//                                Text(
//                                    text = if (infoTerminal.password == "") "${commerce?.password}" else "${infoTerminal.password}",
//                                    fontSize = 14.sp,
//                                    color = color_fontbtn
//                                )
//                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 3.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = "${stringResource(id = R.string.serial)}: ",
                                    color = color_black,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = getSerialPost(context),
                                    fontWeight = FontWeight.Bold,
                                    color = color_black
                                )


                            }

                        }

                    }
                }
                1 -> {

                    NewPasswordTemplate(passwordValue, passwordConfirmationValue)
                }
            }
        }
        Column(modifier = Modifier.align(Alignment.BottomCenter)) {
            btnFun(backendViewModel, tabMenu, passwordValue.value, passwordConfirmationValue.value)
        }
    }

}

@SuppressLint("UnrememberedMutableState")
@Composable
private fun btnFun(backendViewModel: BackendViewModel, numTab: Int, pass: String, pass2: String) {
    val infoTerminal by mutableStateOf(backendViewModel.InfoTerminal.value)
    val context = LocalContext.current
    val commerce = iCommerce.getCommerce(context)

    if (numTab <= 0) {
        if (infoTerminal.rif != null) {
            BtnNext(
                text = stringResource(id = R.string.guardar),
                onClick = {
                    iCommerce.setCommerce(
                        context, iCommerce(
                            tipo = infoTerminal.typeCedula,
                            razonSocial = infoTerminal.nameCommerce,
                            rif = infoTerminal.cedula,
                            telefono = infoTerminal.telefono,
                            password = infoTerminal.password
                        )
                    )
                    GoToActivity(iNameActivity.MAIN, context, true)
                },
                enabled = true,
                ico = painterResource(id = R.drawable.ic_next),
                modifier = Modifier
                    .padding(4.dp)
                    .height(49.dp)
                    .width(240.dp)
            )
        } else {
            BtnNext(
                text = stringResource(id = R.string.sincronizar),
                onClick = {
                    backendViewModel.getSerial(getSerialPost(context))
                },
                enabled = true,
                ico = painterResource(id = R.drawable.ic_next),
                modifier = Modifier
                    .padding(4.dp)
                    .height(49.dp)
                    .width(240.dp)
            )
            if (commerce != null) {
                BtnNext(
                    text = stringResource(id = R.string.olvidar_contraseña),
                    onClick = {
                        startCardLogin {
                            NavTabContext.NextTab()
                        }
                    },
                    enabled = true,
                    ico = painterResource(id = R.drawable.ic_next),
                    modifier = Modifier
                        .padding(4.dp)
                        .height(49.dp)
                        .width(240.dp)
                )
            }

        }

        BtnNext(
            text = stringResource(id = R.string.home),
            onClick = {
                GoToActivity(iNameActivity.MAIN, context, true)
            },
            enabled = true,
            ico = painterResource(id = R.drawable.ic_next),
            modifier = Modifier
                .padding(4.dp)
                .height(49.dp)
                .width(240.dp)
        )
    } else {
        BtnNext(
            text = stringResource(id = R.string.guardar),
            onClick = {
                iCommerce.setCommerce(
                    context,
                    iCommerce(
                        tipo = "${commerce?.tipo}",
                        razonSocial = "${commerce?.razonSocial}",
                        rif = "${commerce?.rif}",
                        telefono = "${commerce?.telefono}",
                        password = pass,
                    )
                )
                GoToActivity(iNameActivity.MAIN, context, true)
            },
            enabled = pass == pass2 && pass.length >= 6 && pass2.length >= 6,
            ico = painterResource(id = R.drawable.ic_next),
            modifier = Modifier
                .padding(4.dp)
                .height(49.dp)
                .width(240.dp)
        )
        BtnNext(
            text = stringResource(id = R.string.last),
            onClick = {
                NavTabContext.LastTab()
            },
            enabled = true,
            ico = painterResource(id = R.drawable.ic_next),
            modifier = Modifier
                .padding(4.dp)
                .height(49.dp)
                .width(240.dp)
        )
    }
}

//
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