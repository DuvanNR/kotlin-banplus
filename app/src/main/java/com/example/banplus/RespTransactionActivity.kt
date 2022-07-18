package com.example.banplus

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.banplus._interface.iTransaction
import com.example.banplus.ui.theme.BanplusTheme
import com.example.banplus.views.RespTransaction

class RespTransactionActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val ObjectIntent: Intent = intent
        val mainactivityintent = Intent(this,MainActivity::class.java )
        setContent {
            BanplusTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val transaction = iTransaction(
                        tipo = "${ObjectIntent.getStringExtra("tipo")}",
                        cedula = "${ObjectIntent.getStringExtra("cedula")}",
                        telefono = "${ObjectIntent.getStringExtra("telefono")}",
                        banco = "${ObjectIntent.getStringExtra("banco")}",
                        monto = "${ObjectIntent.getStringExtra("monto")}",
                    )

                    RespTransaction(iData = transaction, onClickMainActivity= {
                        startActivity(mainactivityintent)
                        finish()
                    })
                }
            }
        }
    }
}

