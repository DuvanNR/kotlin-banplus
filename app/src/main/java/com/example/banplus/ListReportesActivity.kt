package com.example.banplus

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.banplus.ui.theme.BanplusTheme
import com.example.banplus.views.listReportView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListReportesActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val mainActivity = Intent(this,MainActivity::class.java )
        super.onCreate(savedInstanceState)
        setContent {
            BanplusTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background

                ) {
                    listReportView(onEventActiviryMain = {
                        startActivity(mainActivity)
                        finish()

                    })
                }
            }
        }
    }
}
