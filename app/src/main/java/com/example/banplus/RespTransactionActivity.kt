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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.banplus._interface.iTransaction
import com.example.banplus.db.schema.Commerce
import com.example.banplus.inject_dependency.HiltInjectApp
import com.example.banplus.ui.theme.BanplusTheme
import com.example.banplus.viewmodel.CommerceViewModel
import com.example.banplus.views.RespTransaction
import com.nexgo.oaf.apiv3.DeviceEngine
import com.nexgo.oaf.apiv3.device.printer.AlignEnum
import com.nexgo.oaf.apiv3.device.printer.GrayLevelEnum
import com.nexgo.oaf.apiv3.device.printer.Printer
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
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
            deviceEngine = (application as HiltInjectApp).deviceEngine
            printer = deviceEngine!!.printer
            printer?.setTypeface(Typeface.DEFAULT)
            BanplusTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background,
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
                        ref = "${ObjectIntent.getStringExtra("ref")}",
                    )

                    RespTransaction(iData = transaction,
                        onclickimprimir = { onClickimprimir(this, transaction, commerce = it) },
                        onClickMainActivity = {
                            startActivity(mainactivityintent)
                            finish()
                        })
                }
            }
        }
    }
    fun onClickimprimir(athis: Context, resp: iTransaction, commerce: Commerce) {
        printer!!.initPrinter() //init printer
        printer!!.setTypeface(Typeface.DEFAULT) //change print type
        printer!!.setLetterSpacing(3) //change the line space between each line
        printer!!.setGray(GrayLevelEnum.LEVEL_2) //change print gray
        printer!!.appendPrnStr(
           "${commerce.razonSocial}",
            FONT_SIZE_NORMAL,
            AlignEnum.CENTER,
            false
        )
        printer!!.appendPrnStr(
            "RIF: ${commerce.tipo}-${commerce.rif}",
            FONT_SIZE_NORMAL,
            AlignEnum.CENTER,
            false
        )
        printer!!.appendPrnStr(
            "Fecha:",
            resp.fecha,
            FONT_SIZE_NORMAL,
            false
        )
        printer!!.appendPrnStr(
            "Hora:",
            resp.hora,
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
            "${resp.ref}",
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

