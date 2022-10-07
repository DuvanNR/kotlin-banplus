package com.example.banplus.template

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.banplus.R
import com.example.banplus._interface.iCommerce
import com.example.banplus.component.BtnNext
import com.example.banplus.component.TransparentTextField
import com.example.banplus.component.header.HeaderInit
import com.example.banplus.context.LoadingContext
import com.example.banplus.context.LoginContext
import com.example.banplus.ui.theme.color_danger
import com.example.banplus.utils.GoToActivity
import com.example.banplus.utils.iNameActivity
import java.util.*
import kotlin.concurrent.timerTask


@Composable
fun LoginTemplate() {
    Body()

}

@Composable
private fun Body() {
    val passwordValue = rememberSaveable { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    var passwordVisibility by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val commerce = iCommerce.getCommerce(context)

    var dialog by remember {
        mutableStateOf(false)
    }
    if (dialog) {
        SimpleDialog {
            dialog = false
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderInit(iconBoolean = true, menu = false)
        Box(modifier = Modifier.padding(horizontal = 30.dp)) {
            TransparentTextField(
                textFieldValue = passwordValue,
                textLabel = "Password",
                keyboardType = KeyboardType.Password,
                keyboardActions = KeyboardActions(onDone = {
                    focusManager.clearFocus()
                }),
                imeAction = ImeAction.Done,
                trailingIcon = {
                    IconButton(onClick = {
                        passwordVisibility = !passwordVisibility
                    }) {
                        Icon(
                            painter = if (passwordVisibility) {
                                painterResource(id = R.drawable.visibility)
                            } else {

                                painterResource(id = R.drawable.visibility_off)
                            }, contentDescription = "Toggle Password Icon"
                        )
                    }
                },
                visualTransformation = if (passwordVisibility) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                }
            )

        }

        btnAction {
            if ("${commerce?.password}" == passwordValue.value) {
                LoginContext.CloseCardLogin(true)
            } else {
                dialog = true
                LoginContext.addLoginIntneto(context)
            }
        }
    }

}


@Composable
private fun btnAction(clickDialog: () -> Unit) {
    val context = LocalContext.current
    Column() {
        BtnNext(
            text = stringResource(id = R.string.validar),
            onClick = clickDialog,
            enabled = true,
            ico = painterResource(id = R.drawable.ic_next),
            modifier = Modifier
                .padding(4.dp)
                .height(49.dp)
                .width(240.dp)
        )
        BtnNext(
            text = stringResource(id = R.string.home),
            onClick = {
                GoToActivity(iNameActivity.MAIN, context, true)
                LoadingContext.CloseDelay()
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


@Composable
fun SimpleDialog(onDismissRequest: () -> Unit) {
    Dialog(
        onDismissRequest = onDismissRequest,
        content = {
            Surface(shape = RoundedCornerShape(8.dp)) {
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .width(200.dp)
                        .height(100.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            modifier = Modifier.fillMaxSize(0.18F),
                            tint = color_danger,
                            painter = painterResource(id = R.drawable.ic_circle_error),
                            contentDescription = stringResource(id = R.string.default_icon)
                        )
                        Text(text = "Error", style = MaterialTheme.typography.h6)
                    }

                    Box(
                        modifier = Modifier,
                        contentAlignment = Alignment.Center,
                    ) {

                        Text(text = "Credenciales Incorrectas")

                    }
//
                    Button(onClick = onDismissRequest) {
                        Text("Cancel")
                    }
                }
            }
        },
    )
}