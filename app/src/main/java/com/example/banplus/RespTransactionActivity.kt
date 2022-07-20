package com.example.banplus

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.banplus._interface.iTransaction
import com.example.banplus.ui.theme.BanplusTheme
import com.example.banplus.utils.NexgoApplication
import com.example.banplus.views.RespTransaction
import com.nexgo.oaf.apiv3.DeviceEngine
import com.nexgo.oaf.apiv3.device.printer.AlignEnum
import com.nexgo.oaf.apiv3.device.printer.GrayLevelEnum
import com.nexgo.oaf.apiv3.device.printer.Printer

class RespTransactionActivity : ComponentActivity() {
    private var deviceEngine: DeviceEngine? = null
    private var printer: Printer? = null
    private val FONT_SIZE_SMALL = 16
    private val FONT_SIZE_NORMAL = 24
    private val FONT_SIZE_BIG = 30
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val ObjectIntent: Intent = intent
        val mainactivityintent = Intent(this, MainActivity::class.java)
        setContent {
            deviceEngine = (application as NexgoApplication).deviceEngine
            printer = deviceEngine!!.printer
            printer?.setTypeface(Typeface.DEFAULT)
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
                        nameBanco = "${ObjectIntent.getStringExtra("nameBanco")}",
                        hora = "${ObjectIntent.getStringExtra("hora")}",
                        fecha = "${ObjectIntent.getStringExtra("fecha")}",
                    )

                    RespTransaction(iData = transaction,
                        onclickimprimir = { onClickimprimir(this, transaction) },
                        onClickMainActivity = {
                            startActivity(mainactivityintent)
                            finish()
                        })
                }
            }
        }
    }
    fun onClickimprimir(athis: Context, resp: iTransaction) {
        printer!!.initPrinter() //init printer
        printer!!.setTypeface(Typeface.DEFAULT) //change print type
        printer!!.setLetterSpacing(3) //change the line space between each line
        printer!!.setGray(GrayLevelEnum.LEVEL_2) //change print gray
        printer!!.appendPrnStr(
           "Distribuciones Globales",
            FONT_SIZE_NORMAL,
            AlignEnum.CENTER,
            false
        )
        printer!!.appendPrnStr(
            "RIF: J-123456789",
            FONT_SIZE_NORMAL,
            AlignEnum.CENTER,
            false
        )
        printer!!.appendPrnStr(
            "Fecha:",
            "",
            FONT_SIZE_NORMAL,
            false
        )
        printer!!.appendPrnStr(
            "Hora:",
            "",
            FONT_SIZE_NORMAL,
            false
        )
        printer!!.appendPrnStr(
            "Cedula:",
            "${resp.cedula}",
            FONT_SIZE_NORMAL,
            false
        )
        printer!!.appendPrnStr(
            "Telefono:",
            "${resp.telefono}",
            FONT_SIZE_NORMAL,
            false
        )
        printer!!.appendPrnStr(
            "Banco:",
            "${resp.nameBanco}",
            FONT_SIZE_NORMAL,
            false
        )
        printer!!.appendPrnStr(
            "Ref:",
            "x-xxxxxxxxxxxxx",
            FONT_SIZE_NORMAL,
            false
        )

        printer!!.appendPrnStr(
            "Monto: Bs. ${resp.monto}",
            FONT_SIZE_BIG,
            AlignEnum.CENTER,
            false
        )
        printer!!.appendPrnStr(
            "",
            "",
            FONT_SIZE_BIG,
            false
        )
        printer!!.appendPrnStr(
            "",
            "",
            FONT_SIZE_BIG,
            false
        )
        printer!!.appendPrnStr(
            "Firma: __________________",
            FONT_SIZE_NORMAL,
            AlignEnum.LEFT,
            false
        )
        printer!!.appendPrnStr(
            "",
            "",
            FONT_SIZE_BIG,
            false
        )
        printer!!.startPrint(true) { retCode ->

            runOnUiThread {
                Toast.makeText(
                    athis,
                    retCode.toString() + "",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }
}

