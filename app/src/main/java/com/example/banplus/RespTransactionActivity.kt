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
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.toLowerCase
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
import java.text.DecimalFormat


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
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background,
                ) {
                    val status = "${ObjectIntent.getStringExtra("status")}"
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
                        message = "${ObjectIntent.getStringExtra("message")}",
                    )

                    RespTransaction(iData = transaction,
                        onclickimprimir = { onClickimprimir(this, transaction, commerce = it, status = status.toBoolean()) },
                        onClickMainActivity = {
                            startActivity(mainactivityintent)
                            finish()
                        },
                        status = status.toBoolean()
                    )
                }
            }
        }
    }
    fun onClickimprimir(athis: Context, resp: iTransaction, commerce: Commerce, status: Boolean) {
        val forma = DecimalFormat("#,##0.00")
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
            "RIF: ${commerce.tipo.capitalize()}-${commerce.rif}",
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
            "${resp.tipo.capitalize()}-${resp.cedula}",
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
            "Estado:",
            if(status) "Aprobado" else "NEGADA",
            FONT_SIZE_NORMAL,
            false
        )
      if(status) {
          printer!!.appendPrnStr(
              "Ref:",
              "${resp.ref}",
              FONT_SIZE_NORMAL,
              false
          )
      }
        printer!!.appendPrnStr(
            "Monto: Bs. ${forma.format("${resp.monto}".toFloat())}",
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

        if(status) {
            printer!!.appendPrnStr(
                "Firma: __________________________",
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
            printer!!.appendPrnStr(
                "Cedula: __________________________",
                FONT_SIZE_NORMAL,
                AlignEnum.LEFT,
                false
            )
        }
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

