package com.example.banplus.component

import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.banplus._interface.idropdown

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DropdownDemo(
    modifier: Modifier = Modifier,
    selectedOptionText: idropdown, onValueChange: (idropdown) -> Unit, label:String, options:List<idropdown>) {
    var expanded by remember { mutableStateOf(false) }
    ExposedDropdownMenuBox(
        expanded = expanded,
        modifier= modifier
        ,onExpandedChange = {
            expanded = !expanded
        }
    ) {
        OutlinedTextField (
            readOnly = true,
            modifier = modifier,
            value = "${selectedOptionText.title}",
            onValueChange = { onValueChange(idropdown(key="$it", title="${selectedOptionText.title}"))},
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
            options.forEach {
                DropdownMenuItem(
                    modifier = modifier,
                    onClick = {
                        onValueChange(idropdown(key = it.key, title = it.title))
                        expanded = false
                    }
                ) {
                    Text(text = "${it.title}")
                }
            }
        }
    }
}