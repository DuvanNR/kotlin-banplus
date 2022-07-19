package com.example.banplus.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.banplus._interface.iReportes
import com.example.banplus.api.reportes.response.ReportesResponse
import com.example.banplus.ui.theme.*

@Composable
fun listReport(item: ReportesResponse.Movimiento) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color_white)
            .border(1.dp, color_black)
            .padding(vertical = 13.dp, horizontal = 22.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            modifier = Modifier
        ) {
            Text(text = "Ref:  ", color = color_fontbtn, fontWeight = FontWeight.Bold)
            Text(text = "${item.refeOper}", color = color_fontbtn)
        }
        Row(
            modifier = Modifier
        ) {
            Text(text = "Teléfono:  ", color = color_fontbtn, fontWeight = FontWeight.Bold)
            Text(text = "${item.numeroCuenta}", color = color_fontbtn)
        }
        Row(
            modifier = Modifier
        ) {
            Text(text = "Cédula:  ", color = color_fontbtn, fontWeight = FontWeight.Bold)
            Text(text = "${item.tipoMovimiento}", color = color_fontbtn)
        }
        Row(
            modifier = Modifier
        ) {
            Text(text = "Banplus", color = color_fontbtn, fontWeight = FontWeight.Bold)
        }
        Row(
            modifier = Modifier
        ) {
            Text(text = "Fecha:  ", color = color_fontbtn, fontWeight = FontWeight.Bold)
            Text(text = "${item.fechaProceso}", color = color_fontbtn)
        }
        Row(
            modifier = Modifier
        ) {
            Text(text = "Hora:  ", color = color_fontbtn, fontWeight = FontWeight.Bold)
            Text(text = "${item.horaProceso}", color = color_fontbtn)
        }
        Row(
            modifier = Modifier
        ) {
            Text(text = "${item.monto}", color = color_fontbtn, fontWeight = FontWeight.Bold)
            Text(
                text = "${item.conceptoMotivo}",
                color = getIcon("${item.conceptoMotivo}"),
                modifier = Modifier.padding(start = 8.dp),
                fontWeight = FontWeight.Bold
            )
        }
    }


}

fun getIcon(text: String): Color {
    when (text) {
        "Aprobada" -> return color_success
        else -> return color_danger
    }
}

