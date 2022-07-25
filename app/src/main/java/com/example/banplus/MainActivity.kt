package com.example.banplus

import android.content.Intent
import android.os.Bundle
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
import com.example.banplus.ui.theme.BanplusTheme
import com.example.banplus.viewmodel.ReportesViewModel
import com.example.banplus.viewmodel.VueltoViewModel
import com.nexgo.common.LogUtils
import dagger.hilt.android.AndroidEntryPoint
import com.example.banplus.navigation.Router

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?, ) {
        super.onCreate(savedInstanceState)

        val cardtransaction = Intent(this,RespTransactionActivity::class.java )
        val detailReportes = Intent(this,ListReportesActivity::class.java )
        setContent {
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
                        }

                    )
                }

            }

        }


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
}

@Composable
fun TestView(viewModel: ReportesViewModel = hiltViewModel()) {

    Button(onClick = {
    }) {

    }


}