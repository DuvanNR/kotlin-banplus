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
import com.example.banplus.ui.theme.BanplusTheme
import com.example.banplus.viewmodel.VueltoViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: VueltoViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val CardTransaction = Intent(this,RespTransactionActivity::class.java )
        setContent {
            BanplusTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Router(viewModelVuelto = viewModel, onEventTransction ={
                        CardTransaction.putExtra("cedula", it.cedula )
                        CardTransaction.putExtra("tipo", it.tipo )
                        CardTransaction.putExtra("telefono", it.telefono )
                        CardTransaction.putExtra("banco", it.banco )
                        CardTransaction.putExtra("monto", it.monto )
                        startActivity(CardTransaction)
                        finish()
                    })
                }
            }
        }
    }
}

