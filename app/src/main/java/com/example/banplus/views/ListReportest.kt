package com.example.banplus.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.banplus.R
import com.example.banplus._interface.iReportes
import com.example.banplus.component.header.HeaderInit
import com.example.banplus.component.listReport
import androidx.navigation.NavController
import com.example.banplus.api.ApiResponseStatus
import com.example.banplus.api.reportes.response.ReportesResponse
import com.example.banplus.component.LoadingWheel
import com.example.banplus.component.errorDialog
import com.example.banplus.viewmodel.ReportesViewModel

@Composable
fun listReportView(viewModel: ReportesViewModel = hiltViewModel()) {
    val status: ApiResponseStatus<Any>? = viewModel.status.value
    Scaffold() {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            HeaderInit(icon = R.drawable.ic_recurso_4)

            LazyColumn(modifier = Modifier.padding(horizontal = 44.dp)) {
                if (!viewModel?.resp?.value.isNullOrEmpty()) {
                    val itemRepost: List<ReportesResponse.Movimiento> = viewModel.resp.value;
                    println("ddddddddddddddddddd ${viewModel?.resp?.value[0]}")
                    items(itemRepost) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(vertical = 10.dp)
                        ) {
                            listReport(item = it)
                        }

                    }

                }

            }
            if (status is ApiResponseStatus.Loading) {
                LoadingWheel()
            } else if (status is ApiResponseStatus.Error) {
                errorDialog(
                    description = stringResource(id = status.messageId),
                    onDialogDismiss = {})
            }
        }
    }
}

