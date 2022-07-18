package com.example.banplus.component

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.banplus.R
import com.example.banplus.api.ApiResponseStatus
import com.example.banplus.api.vuelto.response.Tranferp2pResponse

@Composable
fun errorDialog(
   description: String
, onDialogDismiss: () -> Unit
) {
    AlertDialog(onDismissRequest = {onDialogDismiss()},
        title ={ Text(text = stringResource(id = R.string.error_dialog_title)) },
        text = { Text(text = description)},
        confirmButton = {}
    )
}