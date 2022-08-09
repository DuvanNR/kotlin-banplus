package com.example.banplus.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.input.KeyboardType
import com.example.banplus.R
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
        label = stringResource(id = R.string.monto).capitalize(),
        visualTransformation = CurrencyAmountInputVisualTransformation(
            fixedCursorAtTheEnd =false
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.NumberPassword
        ),
        modifier = Modifier.fillMaxWidth(),
    )
}