package com.example.banplus

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
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
import com.example.banplus.db.schema.Commerce
import com.example.banplus.db.schema.TransCount
import com.example.banplus.inject_dependency.HiltInjectApp
import com.example.banplus.navigation.PathRouter
import com.example.banplus.navigation.Router
import com.example.banplus.ui.theme.BanplusTheme
import com.example.banplus.utils.checkForInternet
import com.example.banplus.utils.getDatetime
import com.example.banplus.viewmodel.ReportesViewModel
import com.nexgo.common.LogUtils
import com.nexgo.oaf.apiv3.DeviceEngine
import com.nexgo.oaf.apiv3.device.printer.AlignEnum
import com.nexgo.oaf.apiv3.device.printer.GrayLevelEnum
import com.nexgo.oaf.apiv3.device.printer.Printer
import dagger.hilt.android.AndroidEntryPoint
import java.text.DecimalFormat

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private var deviceEngine: DeviceEngine? = null
    private var printer: Printer? = null
    private val FONT_SIZE_SMALL = 16
    private val FONT_SIZE_NORMAL = 24
    private val FONT_SIZE_BIG = 30
    public var title_view = ""

    override fun onCreate(savedInstanceState: Bundle?) {
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
                        title_view = {
                            title_view = it
                        },
                        onEventTransction ={it, status ->
                            onEventTransation(it,cardtransaction, status)},
                        onGoToReportes = {
                            startActivity(detailReportes)
                        },
                        IrEditTerminal = {
                            onNavigateConfigPost(postActiviry)
                        },
                        onPrintDetails = {a,b ->
                            onClickimprimir(this,a,b)},
                        checkForInternet = { navNa, type->
                            val aa = checkForInternet(this)
                                if(type == getString(R.string.vuelto)) {

                                    if (aa) {
                                    navNa.navigate(PathRouter.VueltoRoute.route)
                                } else {
                                        Toast.makeText(this, R.string.no_conexion_internet, Toast.LENGTH_SHORT).show()
                                    }
                           }
                            if(type == getString(R.string.reporte)){
                                navNa.navigate(PathRouter.ReporteRoute.route)
                            }
                        }

                    )
                }

            }

        }


    }
    fun onNavigateConfigPost(PostActivity: Intent) {
        PostActivity.putExtra("status", true )
        startActivity(PostActivity)
    }
    override fun onBackPressed() {

        println("1111111111111111111${title_view}")
        return
    }
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        val mainActiviry = Intent(this,MainActivity::class.java )
        if(title_view === "vuelto") {
            startActivity(mainActiviry)
        }
        return if (keyCode == KeyEvent.KEYCODE_BACK) {

            true
        } else super.onKeyDown(keyCode, event)
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

    //código
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            hideSystemUI()
        }

    }

    private fun hideSystemUI() {
        val decorView = window.decorView
        decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }
    override fun onKeyUp(keyCode: Int, event: KeyEvent): Boolean {
        return when (keyCode) {
            KeyEvent.KEYCODE_D -> {
                hideSystemUI()
                true
            }
            KeyEvent.KEYCODE_F -> {
                hideSystemUI()
                true
            }
            KeyEvent.KEYCODE_J -> {
                hideSystemUI()
                true
            }
            KeyEvent.KEYCODE_K -> {
                hideSystemUI()
                true
            }
            else -> {
                hideSystemUI()
                super.onKeyUp(keyCode, event)
            }
        }
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
            "${getString(R.string.rif)}: ${commerce.tipo.capitalize()}-${commerce.rif}",
            FONT_SIZE_NORMAL,
            AlignEnum.CENTER,
            false
        )
        printer!!.appendPrnStr(
            "${getString(R.string.fecha)}:",
            data.fecha,
            FONT_SIZE_NORMAL,
            false
        )
        printer!!.appendPrnStr(
            "${getString(R.string.hora)}:",
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
            getString(R.string.pago_plus),
            "",
            FONT_SIZE_NORMAL,
            false
        )
        printer!!.appendPrnStr(
            getString(R.string.cantidad),
            "${resp.total}",
            FONT_SIZE_NORMAL,
            false
        )
        printer!!.appendPrnStr(
            getString(R.string.monto).capitalize(),
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
                        getString(R.string.no_papel_imprimir),
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