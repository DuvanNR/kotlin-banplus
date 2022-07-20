package com.example.banplus

import Router
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.banplus._interface.iTransaction
import com.example.banplus.ui.theme.BanplusTheme
import com.example.banplus.viewmodel.VueltoViewModel
import com.nexgo.common.LogUtils

class MainActivity : ComponentActivity() {
    private val viewModel: VueltoViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val CardTransaction = Intent(this,RespTransactionActivity::class.java )
        val DetailReportes = Intent(this,ListReportesActivity::class.java )
        setContent {

            LogUtils.setDebugEnable(true)
            BanplusTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Router(
                        viewModelVuelto = viewModel,
                        onEventTransction ={onEventTransation(it,CardTransaction)},
                        onGoToReportes = {
                            startActivity(DetailReportes)
                        },

                    )
                }

            }

        }


    }

    fun onEventTransation(it: iTransaction, intent: Intent) {
        intent.putExtra("cedula", it.cedula )
        intent.putExtra("tipo", it.tipo )
        intent.putExtra("telefono", it.telefono )
        intent.putExtra("banco", it.banco )
        intent.putExtra("monto", it.monto )
        intent.putExtra("nameBanco", it.nameBanco )
        startActivity(intent)
        finish()
    }
}

