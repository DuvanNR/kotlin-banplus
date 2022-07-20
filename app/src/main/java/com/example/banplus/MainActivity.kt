package com.example.banplus

import Router
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.widget.Toast
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
import com.nexgo.oaf.apiv3.DeviceEngine
import com.nexgo.oaf.apiv3.device.printer.AlignEnum
import com.nexgo.oaf.apiv3.device.printer.GrayLevelEnum
import com.nexgo.oaf.apiv3.device.printer.Printer
import com.example.banplus.utils.NexgoApplication
import com.nexgo.common.LogUtils
import java.util.logging.Logger

class MainActivity : ComponentActivity() {
    private val viewModel: VueltoViewModel by viewModels()
    private var log: Logger? = null
    private var deviceEngine: DeviceEngine? = null
    private var printer: Printer? = null
    private val FONT_SIZE_NORMAL = 24

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val CardTransaction = Intent(this,RespTransactionActivity::class.java )
        val DetailReportes = Intent(this,ListReportesActivity::class.java )
        setContent {
            deviceEngine = (application as NexgoApplication).deviceEngine
            printer = deviceEngine!!.printer
            printer?.setTypeface(Typeface.DEFAULT)
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
                        onClickPrint = {
                            printer!!.initPrinter() //init printer

                            printer!!.setTypeface(Typeface.DEFAULT) //change print type

                            printer!!.setLetterSpacing(3) //change the line space between each line

                            printer!!.setGray(GrayLevelEnum.LEVEL_2) //change print gray

                            printer!!.appendPrnStr(
                                getString(R.string.app_name),
                                FONT_SIZE_NORMAL,
                                AlignEnum.LEFT,
                                false
                            )
                            printer!!.appendPrnStr(
                                getString(R.string.print_merchantno),
                                FONT_SIZE_NORMAL,
                                AlignEnum.LEFT,
                                false
                            )
                            printer!!.appendPrnStr(
                                getString(R.string.print_terminalno),
                                getString(R.string.print_operator),
                                FONT_SIZE_NORMAL,
                                false
                            )
                            printer!!.appendPrnStr(
                                getString(R.string.print_issurebank),
                                FONT_SIZE_NORMAL,
                                AlignEnum.LEFT,
                                false
                            )
                            printer!!.appendPrnStr(
                                getString(R.string.print_shoudan),
                                FONT_SIZE_NORMAL,
                                AlignEnum.LEFT,
                                false
                            )

                            printer!!.startPrint(true) { retCode ->

                                runOnUiThread {
                                    Toast.makeText(
                                        this@MainActivity,
                                        retCode.toString() + "",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }

                        }
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
        startActivity(intent)
        finish()
    }
}

