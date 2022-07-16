package com.example.banplus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.banplus.navigation.Router
import com.example.banplus.ui.theme.BanplusTheme
import com.example.banplus.viewmodel.VueltoViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: VueltoViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BanplusTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    val status = viewModel.status
                    Router(status.value, onClick = { cedula,
                                                     cell,
                                                     banco,
                                                     tipo,
                                                     monto ->
                        println("....................cedula,\n" +
                                "                                                     cell,\n" +
                                "                                                     banco,\n" +
                                "                                                     tipo,\n" +
                                "                                                     monto .")
                        viewModel.EmitPago(
                            tipo = tipo,
                            cedula, cell,
                            banco = banco,
                            monto = monto
                        )


                    })
                }
            }
        }
    }
}

