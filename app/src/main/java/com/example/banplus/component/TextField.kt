package com.example.banplus.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TransformedText
import com.example.banplus.utils.isNumber

@Composable
fun PostField(
    text: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
) {
    OutlinedTextField(
        value = text,
        onValueChange = { onValueChange(it) },
        label = {
            Text(label)
        },
        modifier = modifier,
        singleLine = true,
        keyboardOptions = keyboardOptions,
    )
}