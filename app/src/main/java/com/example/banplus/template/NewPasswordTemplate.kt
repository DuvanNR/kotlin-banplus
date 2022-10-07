package com.example.banplus.template

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.example.banplus.component.TransparentTextField
import com.example.banplus.ui.theme.color_danger


@Composable
fun NewPasswordTemplate(
    passwordValue: MutableState<String>,
    passwordConfirmationValue: MutableState<String>
) {
    val focusManager = LocalFocusManager.current
    var passwordVisibility by remember { mutableStateOf(false) }

    var dialog by remember {
        mutableStateOf(false)
    }
    if (dialog) {
        NewPasswordDialog {
            dialog = false
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier.padding(horizontal = 30.dp)) {
            Column() {

                TransparentTextField(
                    textFieldValue = passwordValue,
                    textLabel = "Contraseña",
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
                TransparentTextField(
                    textFieldValue = passwordConfirmationValue,
                    textLabel = "Confirmar contraseña",
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

        }

    }

}


@Composable
fun NewPasswordDialog(onDismissRequest: () -> Unit) {
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