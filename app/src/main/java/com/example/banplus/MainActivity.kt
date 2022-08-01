package com.example.banplus

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.banplus._interface.iTransaction
import com.example.banplus.api.vuelto.response.Tranferp2pResponse
import com.example.banplus.db.schema.Commerce
import com.example.banplus.db.schema.TransCount
import com.example.banplus.inject_dependency.HiltInjectApp
import com.example.banplus.ui.theme.BanplusTheme
import com.example.banplus.viewmodel.ReportesViewModel
import com.example.banplus.viewmodel.VueltoViewModel
import com.nexgo.common.LogUtils
import dagger.hilt.android.AndroidEntryPoint
import com.example.banplus.navigation.Router
import com.example.banplus.utils.getDatetime
import com.nexgo.oaf.apiv3.DeviceEngine
import com.nexgo.oaf.apiv3.device.printer.AlignEnum
import com.nexgo.oaf.apiv3.device.printer.GrayLevelEnum
import com.nexgo.oaf.apiv3.device.printer.Printer
import java.text.DecimalFormat

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private var deviceEngine: DeviceEngine? = null
    private var printer: Printer? = null
    private val FONT_SIZE_SMALL = 16
    private val FONT_SIZE_NORMAL = 24
    private val FONT_SIZE_BIG = 30

    override fun onCreate(savedInstanceState: Bundle?, ) {
        super.onCreate(savedInstanceState)

        val cardtransaction = Intent(this,RespTransactionActivity::class.java )
        val detailReportes = Intent(this,ListReportesActivity::class.java )
        val postActiviry = Intent(this,EditPostActivity::class.java )
        setContent {
            deviceEngine = (application as HiltInjectApp).deviceEngine
            printer = deviceEngine!!.printer
            printer?.setTypeface(Typeface.DEFAULT)
            LogUtils.setDebugEnable(true)
            BanplusTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Router(
                        onEventTransction ={it, status ->
                            onEventTransation(it,cardtransaction, status)},
                        onGoToReportes = {
                            startActivity(detailReportes)
                        },
                        IrEditTerminal = {
                            onNavigateConfigPost(postActiviry)
                        },
                        onPrintDetails = {a,b ->
                            onClickimprimir(this,a,b)}

                    )
                }

            }

        }


    }
    fun onNavigateConfigPost(PostActivity: Intent) {
        PostActivity.putExtra("status", true )
        startActivity(PostActivity)
    }

    fun onEventTransation(it: iTransaction, intent: Intent, status: String) {
        println("__________________${status}__________")
//        println("+++++++_______${event?.msRsB}")
//        val fecha = "${event?.msRsB?.pago?.fechaRespuesta}".substring(0,10)
//        val hora = "${event?.msRsB?.pago?.fechaRespuesta}".substring(11,19)
//        hora.replace(".", ":")
//        println("+++++++_______$fecha")
//        println("+++++++_______$hora")

        intent.putExtra("cedula", it.cedula )
        intent.putExtra("tipo", it.tipo )
        intent.putExtra("telefono", it.telefono )
        intent.putExtra("banco", it.banco )
        intent.putExtra("monto", it.monto )
        intent.putExtra("nameBanco", it.nameBanco )
        intent.putExtra("fecha", it.fecha )
        intent.putExtra("hora",  it.hora)
        intent.putExtra("ref",  it.ref)
        intent.putExtra("message",  it.message)
        intent.putExtra("status",  status)

        startActivity(intent)
        finish()
    }


    fun onClickimprimir(athis: Context, resp: TransCount, commerce: Commerce) {
        val forma = DecimalFormat("#,##0.00")
        val data = getDatetime()
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
            data.fecha,
            FONT_SIZE_NORMAL,
            false
        )
        printer!!.appendPrnStr(
            "Hora:",
            data.hora,
            FONT_SIZE_NORMAL,
            false
        )
        printer!!.appendPrnStr(
            "",
            "",
            FONT_SIZE_NORMAL,
            false
        )

        printer!!.appendPrnStr(
            "Pago Plus",
            "",
            FONT_SIZE_NORMAL,
            false
        )
        printer!!.appendPrnStr(
            "Cantidad:",
            "${resp.total}",
            FONT_SIZE_NORMAL,
            false
        )
        printer!!.appendPrnStr(
            "Monto:",
            "Bs. ${forma.format(resp.amount)}",
            FONT_SIZE_NORMAL,
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
                if(retCode == -1005) {
                    Toast.makeText(
                        athis,
                        "No tiene papel para imprimir!!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                if(retCode == 0) {
                    Toast.makeText(
                        athis,
                        "Imprimiendo",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }
        }

    }
}

@Composable
fun TestView(viewModel: ReportesViewModel = hiltViewModel()) {

    Button(onClick = {
    }) {

    }


}