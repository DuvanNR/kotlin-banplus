package com.example.banplus.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.example.banplus.utils.CurrencyAmountInputVisualTransformation

@Composable
fun inputNumber(
    value: String,
    onNumberChange: (String) -> Unit,
) {
    PostField(
        text = value,
        onValueChange = {
            onNumberChange(it)
        },
        label = "Monto",
        visualTransformation = CurrencyAmountInputVisualTransformation(
            fixedCursorAtTheEnd =false
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.NumberPassword
        ),
        modifier = Modifier.fillMaxWidth(),
    )
}