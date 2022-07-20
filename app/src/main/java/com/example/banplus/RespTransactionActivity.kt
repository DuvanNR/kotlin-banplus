package com.example.banplus

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.banplus._interface.iTransaction
import com.example.banplus.ui.theme.BanplusTheme
import com.example.banplus.views.RespTransaction
import com.nexgo.oaf.apiv3.DeviceEngine
import com.nexgo.oaf.apiv3.device.printer.Printer

class RespTransactionActivity : ComponentActivity() {
    private var deviceEngine: DeviceEngine? = null
    private var printer: Printer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
//        deviceEngine = (application as NexgoApplication).deviceEngine
//        printer = deviceEngine?.printer
//         rinter?.setTypeface(Typeface.DEFAULT)

        super.onCreate(savedInstanceState)
        val ObjectIntent: Intent = intent
        val mainactivityintent = Intent(this, MainActivity::class.java)
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

                    RespTransaction(iData = transaction,
                        onclickimprimir = {
                            println("hola holahoalahoa")

                        },
                        onClickMainActivity = {
                            startActivity(mainactivityintent)
                            finish()
                        })
                }
            }
        }
    }
}

