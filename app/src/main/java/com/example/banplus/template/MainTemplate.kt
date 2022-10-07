package com.example.banplus.template

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.banplus.R
import com.example.banplus.component.BtnIni
import com.example.banplus.component.template.LayoutTemplate
import com.example.banplus.utils.GoToActivity
import com.example.banplus.utils.iNameActivity
import com.example.banplus.viewmodel.VueltoViewModel

@Composable
fun MainTemplate() {
    LayoutTemplate(MenuBoolean = true, children = { Body() })
}

@Composable
private fun Body(viewModel: VueltoViewModel = hiltViewModel()) {
    val context = LocalContext.current
    Box(
        contentAlignment = Alignment.Center, modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            BtnIni(
                text = stringResource(id = R.string.pago_plus),
                ico = painterResource(id = R.drawable.ic_btn2), onClick = {
                    GoToActivity(iNameActivity.VUELTO, context)
                })
            BtnIni(
                text = "Reportes",
                ico = painterResource(id = R.drawable.ic_btn1), onClick = {
                    GoToActivity(iNameActivity.REPORT, context)
                    viewModel.deleteYesterdayInvoice()
                })
        }

    }

}