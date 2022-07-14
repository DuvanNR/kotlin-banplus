package com.example.banplus.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DropdownDemo(selectedOptionText: String, onValueChange: (String) -> Unit, label:String, height:Dp = 70.dp, width:Dp = 120.dp) {
    val options = listOf("v", "c")
    var expanded by remember { mutableStateOf(false) }
    ExposedDropdownMenuBox(
        expanded = expanded,
        modifier= Modifier.size(height = height, width = width)
            .padding(end = 30.dp)
        ,onExpandedChange = {
            expanded = !expanded
        }
    ) {
        OutlinedTextField (
            readOnly = true,
            value = selectedOptionText,
            onValueChange = { onValueChange(it)},
            label = { Text(label) },
            singleLine = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    onClick = {
                        onValueChange(selectionOption)
                        expanded = false
                    }
                ) {
                    Text(text = selectionOption)
                }
            }
        }
    }
}