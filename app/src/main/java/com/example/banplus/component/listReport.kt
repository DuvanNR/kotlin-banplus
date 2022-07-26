package com.example.banplus.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.banplus._interface.iReportes
import com.example.banplus.api.reportes.response.ReportesResponse
import com.example.banplus.db.schema.Transaction
import com.example.banplus.ui.theme.*
import java.text.DecimalFormat

@Composable
fun listReport(item: Transaction = Transaction("","","","",0.00,"","","","","",)) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color_white)
            .border(1.dp, color_black)
            .padding(vertical = 10.dp, horizontal = 10.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            modifier = Modifier
        ) {
            Text(text = "Ref:  ", color = color_fontbtn, fontWeight = FontWeight.Bold)
            Text(text = "${item.ref}", color = color_fontbtn)
        }
        Row(
            modifier = Modifier
        ) {
            Text(text = "Teléfono:  ", color = color_fontbtn, fontWeight = FontWeight.Bold)
            Text(text = "${item.telefono}", color = color_fontbtn)
        }
        Row(
            modifier = Modifier
        ) {
            Text(text = "Cédula:  ", color = color_fontbtn, fontWeight = FontWeight.Bold)
            Text(text = "${item.tipo.capitalize()}-${item.cedula}", color = color_fontbtn)
        }
        Row(
            modifier = Modifier
        ) {
            Text(text = "${item.nameBanco}", color = color_fontbtn, fontWeight = FontWeight.Bold)
        }
        Row(
            modifier = Modifier
        ) {
            Text(text = "Fecha:  ", color = color_fontbtn, fontWeight = FontWeight.Bold)
            Text(text = "${item.fecha}", color = color_fontbtn)

        }
        Row() {
            Text(text = "Hora:  ",
                color = color_fontbtn,
                modifier = Modifier,
                fontWeight = FontWeight.Bold)
            Text(text = "${item.hora}", color = color_fontbtn)
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val forma = DecimalFormat("#,##0.00")
            Text(text = "Bs. ${forma.format(item.monto)}", color = color_fontbtn, fontWeight = FontWeight.Bold)
            Text(
                text = "${item.message}",
                color = getIcon("${item.message}"),
                modifier = Modifier.padding(start = 8.dp),
                fontWeight = FontWeight.Bold
            )
        }
    }


}

fun getIcon(text: String): Color {
    when (text) {
        "Operacion Exitosa" -> return color_success
        else -> return color_danger
    }
}

@Preview(showBackground = true, widthDp = 400, heightDp = 200)
@Composable
fun getPreviewListItem() {
    listReport()
}
