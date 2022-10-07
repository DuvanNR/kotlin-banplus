package com.example.banplus.template

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.banplus.R
import com.example.banplus.component.BtnNext
import com.example.banplus.component.CardReport
import com.example.banplus.component.template.CardComponent
import com.example.banplus.component.template.CardResponse
import com.example.banplus.component.template.LayoutTemplate
import com.example.banplus.context.TransactionContext
import com.example.banplus.db.schema.TransCount
import com.example.banplus.utils.GoToActivity
import com.example.banplus.utils.iNameActivity
import com.example.banplus.utils.onPrintReport
import com.example.banplus.utils.printCardResponse
import com.example.banplus.viewmodel.VueltoViewModel

@Composable
fun ReportTemplate() {
    LayoutTemplate(children = { Body() })
}

@Composable
private fun Body(
    viewModel: VueltoViewModel = hiltViewModel(),
) {
    val totalTransCount by viewModel.TotalTrans.observeAsState(TransCount(0.1, 1))
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        CardComponent(title = "Consolidation Diario", header = true) {
            CardReport(totalTransCount)
        }

        Column() {
            BtnAction(totalTransCount)
        }

    }
}

@Composable
private fun BtnAction(totalTransCount: TransCount) {
    val context = LocalContext.current
    BtnNext(
        text = stringResource(id = R.string.imprimir),
        onClick = {
            onPrintReport(context, totalTransCount)
        },
        ico = painterResource(id = R.drawable.ic_next),
        enabled = true,
        modifier = Modifier
            .padding(4.dp)
            .height(49.dp)
            .width(240.dp)
    )
    BtnNext(
        text = stringResource(id = R.string.ver_detalles),
        onClick = {
            GoToActivity(iNameActivity.LIST_REPORT, context)
//                    onPrintReport(context, totalTransCount)
        },
        ico = painterResource(id = R.drawable.ic_next),
        enabled = true,
        modifier = Modifier
            .padding(4.dp)
            .height(49.dp)
            .width(240.dp)
    )
    BtnNext(
        text = stringResource(id = R.string.home),
        onClick = {
            TransactionContext.reset()
            GoToActivity(iNameActivity.MAIN, context, false)
        },
        ico = painterResource(id = R.drawable.ic_next),
        enabled = true,
        modifier = Modifier
            .padding(4.dp)
            .height(49.dp)
            .width(240.dp)
    )
}
//
//@Composable
//fun Greeting(name: String) {
//    Text(text = "Hello $name!")
//}
//
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview2() {
//    BanplusTheme {
//        Greeting("Android")
//    }
//}