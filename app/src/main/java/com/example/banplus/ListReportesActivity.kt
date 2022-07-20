package com.example.banplus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.banplus.ui.theme.BanplusTheme
import com.example.banplus.utils.NexgoApplication
import com.example.banplus.viewmodel.ReportesViewModel
import com.example.banplus.views.listReportView

class ListReportesActivity : ComponentActivity() {
    private val viewModelReportes: ReportesViewModel by viewModels()
    private val deviceEngine = NexgoApplication()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BanplusTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val status = viewModelReportes.status
                    listReportView(status = status.value, viewModelReportes.resp.value)
                }
            }
        }
    }
}
