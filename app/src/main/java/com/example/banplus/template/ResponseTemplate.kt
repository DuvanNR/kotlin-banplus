package com.example.banplus.template

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.banplus.R
import com.example.banplus.component.BtnNext
import com.example.banplus.component.template.CardComponent
import com.example.banplus.component.template.CardResponse
import com.example.banplus.component.template.LayoutTemplate
import com.example.banplus.context.TransactionContext
import com.example.banplus.utils.GoToActivity
import com.example.banplus.utils.iNameActivity
import com.example.banplus.utils.printCardResponse

@Composable
fun ResponseTemplate() {
    LayoutTemplate(children = { Body() })
}

@Composable
private fun Body() {
    val aa = TransactionContext.getTransaction()
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        CardComponent(title = "Repuesta de la solicitud") {
            CardResponse(data = aa)
        }

        Column() {
            BtnNext(
                text = stringResource(id = R.string.imprimir),
                onClick = {
                    printCardResponse(context, aa)
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

    }

}

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