package com.example.banplus.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.text.input.ImeAction
import com.example.banplus._interface.idropdown
import java.util.*
import kotlin.concurrent.timerTask

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DropdownDemo(
    modifier: Modifier = Modifier,
    textColor: Color = Gray,
    modifierDropdownItem: Modifier = Modifier,
    selectedOptionText: idropdown,
    onValueChange: (idropdown) -> Unit,
    label: String,
    options: List<idropdown>
) {
    var expanded by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    val trailingIconView = @Composable {
        IconButton(
            onClick = {
                focusRequester.requestFocus()
                KeyboardOptions(imeAction = ImeAction.Done)
                expanded = false
            },
        ) {
            Icon(
                Icons.Default.ArrowDropDown, contentDescription = "", tint = Color.Black
            )
        }
    }
    ExposedDropdownMenuBox(expanded = expanded, modifier = modifier, onExpandedChange = {
        Timer().schedule(timerTask {
            expanded = !expanded
        }, 350)
    }) {
        OutlinedTextField(
            modifier = modifier.focusRequester(focusRequester),
            readOnly = true,
            value = "${selectedOptionText.title}",
            colors = TextFieldDefaults.outlinedTextFieldColors(
                disabledPlaceholderColor = textColor, disabledTextColor = textColor
            ),
//            modifier = ,
            onValueChange = {
                onValueChange(
                    idropdown(
                        key = "$it", title = "${selectedOptionText.title}"
                    )
                )
            },
            label = { Text(label) },
            singleLine = true,
            trailingIcon = trailingIconView

        )
        ExposedDropdownMenu(expanded = expanded,
            modifier = modifierDropdownItem,
            onDismissRequest = {
                expanded = false
            }) {
            options.forEach {
                DropdownMenuItem(onClick = {
                    onValueChange(idropdown(key = it.key, title = it.title))
                    expanded = false
                }) {
                    Text(text = "${it.title}")
                }
            }
        }
    }
}