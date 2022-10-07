package com.example.banplus.component

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.banplus.R

@Composable
fun errorDialog(
    description: Int, onDialogDismiss: () -> Unit
) {
    AlertDialog(onDismissRequest = { onDialogDismiss() },
        title = { Text(text = stringResource(id = R.string.error_dialog_title)) },
        text = { Text(text = stringResource(id = description)) },
        confirmButton = {}
    )
}