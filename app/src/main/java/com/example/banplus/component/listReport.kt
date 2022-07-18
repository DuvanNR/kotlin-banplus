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
import com.example.banplus.ui.theme.*

@Composable
fun listReport(item: iReportes) {

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
            Text(text = item.rif, color = color_fontbtn)
        }
        Row(
            modifier = Modifier
        ) {
            Text(text = "Teléfono:  ", color = color_fontbtn, fontWeight = FontWeight.Bold)
            Text(text = item.telefono, color = color_fontbtn)
        }
        Row(
            modifier = Modifier
        ) {
            Text(text = "Cédula:  ", color = color_fontbtn, fontWeight = FontWeight.Bold)
            Text(text = item.cedula, color = color_fontbtn)
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
            Text(text = item.fecha, color = color_fontbtn)
        }
        Row(
            modifier = Modifier
        ) {
            Text(text = "Hora:  ", color = color_fontbtn, fontWeight = FontWeight.Bold)
            Text(text = item.hora, color = color_fontbtn)
        }
        Row(
            modifier = Modifier
        ) {
            Text(text = item.monto, color = color_fontbtn, fontWeight = FontWeight.Bold)
            Text(
                text = item.status,
                color = getIcon(item.status),
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


@Preview(showBackground = true, widthDp = 289, heightDp = 170)
@Composable
fun preViewList() {
    val item = iReportes(
        rif = "0000000111222",
        telefono = "04240000000",
        cedula = "V-12345678",
        fecha = "22/06/2022",
        hora = "8:30 a.m.",
        monto = "Bs. 10.000",
        status = "Aprobada"
    )
    listReport(item)
}