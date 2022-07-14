package com.example.banplus.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.banplus.utils.isNumber

@Composable
fun PostField (text: String, onValueChange:(String) -> Unit, label: String) {
    OutlinedTextField(
        value = text,
        onValueChange = { onValueChange(it)  },
        label = {
            Text(label)
        },
        modifier = Modifier
            .fillMaxWidth(),
        singleLine = true,

    )
}