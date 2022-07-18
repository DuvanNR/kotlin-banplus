package com.example.banplus.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.banplus.R
import com.example.banplus._interface.iReportes
import com.example.banplus.component.header.HeaderInit
import com.example.banplus.component.listReport
import androidx.navigation.NavController

@Composable
fun listReportView(navController:NavController, itemRepost: List<iReportes>) {
    Scaffold() {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            HeaderInit(icon = R.drawable.ic_recurso_4)
            LazyColumn(modifier = Modifier.padding(horizontal = 44.dp)) {
                items(itemRepost) {
                    Box(modifier = Modifier.fillMaxSize().padding(vertical = 10.dp)) {
                        listReport(item = it)
                    }

                }

            }
        }
    }
}

