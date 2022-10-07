package com.example.banplus.views

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.banplus.R
import com.example.banplus.component.BtnNext
import com.example.banplus.component.cardsAlert
import com.example.banplus.component.header.HeaderInit
import com.example.banplus.db.schema.Commerce
import com.example.banplus.db.schema.TransCount
import com.example.banplus.db.schema.Transaction
import com.example.banplus.ui.theme.*
import com.example.banplus.viewmodel.CommerceViewModel
import com.example.banplus.viewmodel.VueltoViewModel

@Composable
fun ViewReportes(onGoToReportes: () -> Unit, onPrintDetails: (TransCount, Commerce) -> Unit) {
    Scaffold() {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderInit(iconBoolean = false,
                menu = false,
                )
            ReportesBody(onClickListRe = { onGoToReportes() }, onPrintDetails = onPrintDetails)
        }

    }

}

@Composable
fun ReportesBody(
    onClickListRe: () -> Unit,
    viewModel: VueltoViewModel = hiltViewModel(),
    viewModelCommerce: CommerceViewModel = hiltViewModel(),
    onPrintDetails: (TransCount, Commerce) -> Unit
) {
    val totalTransCount by viewModel.TotalTrans.observeAsState(TransCount(0.1, 1))
    val commerce by viewModelCommerce.commerce.observeAsState(Commerce("", "", "", ""))

    Box(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 22.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            cardsAlert(detailsData = totalTransCount)

        }

        Column(
            modifier = Modifier.align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            BtnNext(
                text = stringResource(id = R.string.imprimir),
                onClick = {
                    onPrintDetails(totalTransCount, commerce)
                },
                ico = painterResource(id = R.drawable.ic_next),
                modifier = Modifier
                    .padding(4.dp)
                    .height(49.dp)
                    .width(240.dp)
            )
            BtnNext(
                text = stringResource(id = R.string.ver_detalles),
                onClick = onClickListRe,
                ico = painterResource(id = R.drawable.ic_next),
                background = color_fontbtn,
                modifier = Modifier
                    .padding(4.dp)
                    .height(49.dp)
                    .width(240.dp)
            )

        }
    }

}