package com.example.banplus.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun PostField(
    text: String,
    onValueChange: (String) -> Unit,
    label: String,

    modifier: Modifier = Modifier,


    keyboardOptions: KeyboardOptions = KeyboardOptions(),

    visualTransformation: VisualTransformation = VisualTransformation.None,
    readOnly: Boolean = true
) {
    OutlinedTextField(
        enabled = readOnly,
        value = text,
        onValueChange = { onValueChange(it) },
        visualTransformation = visualTransformation,
        label = {
            Text(label)
        },
        modifier = modifier,
        singleLine = true,
        keyboardOptions = keyboardOptions,
    )
}