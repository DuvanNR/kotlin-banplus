package com.example.banplus.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.banplus.R
import com.example.banplus._interface.iTransaction
import com.example.banplus.ui.theme.*

@Composable
fun cardsAlert(
    rif: Boolean = true,
    title: String = "Consolidado Diario",
    status: Boolean = true,
    iData: iTransaction = iTransaction(),
) {

    Card(
        modifier = Modifier.padding(horizontal = 24.dp),
        border = BorderStroke(1.dp, color_black),
        shape = RoundedCornerShape(0.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            Text(
                text = title, color = color_white, modifier = Modifier
                    .background(color_black)
                    .fillMaxWidth()
                    .padding(vertical = 9.dp), textAlign = TextAlign.Center
            )

            if (rif) {
                Text(
                    text = "Distribuciones Globales \n RIF: J-123456789",
                    color = color_black,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 41.dp),
                    textAlign = TextAlign.Center
                )

            }
                when (title) {
                    "Consolidado Diario" -> CardA()
                    "Confirmar Operación" -> CardC(iData)
                    else -> CardB(status = status, dataTransfer =  iData)
                }


//                    Column(modifier = Modifier.fillMaxWidth().align(Alignment.TopCenter).background(
//                       color_black)) {
//
//                   }
        }


    }


}

@Composable
fun CardC(iData: iTransaction) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 34.dp)

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 88.dp)
        ) {
            Text(text = "Cédula:  ", color = color_fontbtn, fontWeight = FontWeight.Bold)
            Text(text = "${iData.tipo}-${iData.cedula}", color = color_fontbtn)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = "Teléfono:  ", color = color_fontbtn, fontWeight = FontWeight.Bold)
            Text(text = "${iData.telefono}", color = color_fontbtn)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = "Banco:  ", color = color_fontbtn, fontWeight = FontWeight.Bold)
            Text(text = "${iData.nameBanco}", color = color_fontbtn)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "MONTO:  ",
                fontSize = 18.sp,
                color = color_fontbtn,
                fontWeight = FontWeight.Bold
            )
            Text(text = "Bs. ${iData.monto}", fontSize = 18.sp, color = color_fontbtn)
        }
    }
}

@Composable
fun CardA() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top=100.dp,start=12.dp)

    ) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(text = "Fecha:  ", color = color_fontbtn, fontWeight = FontWeight.Bold)
        Text(text = "22/06/2022", color = color_fontbtn)
        Text(text = "Hora:  ", modifier = Modifier.padding(start=5.dp),color = color_fontbtn, fontWeight = FontWeight.Bold)
        Text(text = "8:30 a.m.", color = color_fontbtn)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(text = "Pago Plus  ", fontWeight = FontWeight.Bold)
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(text = "Cantidad:  ", color = color_fontbtn, fontWeight = FontWeight.Bold)
        Text(text = "20", color = color_fontbtn)
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 24.dp),
    ) {
        Text(text = "Monto:  ",
            modifier = Modifier,
            color = color_fontbtn, fontWeight = FontWeight.Bold)
        Text(text = "Bs. 10000" ,color = color_fontbtn)
    }
    }

}

@Composable
fun CardB(status: Boolean, dataTransfer: iTransaction) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top=84.dp, start = 25.dp,end=25.dp)

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = "Fecha:  ", color = color_fontbtn, fontWeight = FontWeight.Bold)
            Text(text = dataTransfer.fecha, color = color_fontbtn)
            Text(text = "Hora:  ", modifier = Modifier.padding(start=9.dp),color = color_fontbtn, fontWeight = FontWeight.Bold)
            Text(text = dataTransfer.hora, color = color_fontbtn)
        }


        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = "Cedula:  ", color = color_fontbtn, fontWeight = FontWeight.Bold)
            Text(text = "${dataTransfer.tipo}-${dataTransfer.cedula}", color = color_fontbtn)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = "Teléfono:  ", color = color_fontbtn, fontWeight = FontWeight.Bold)
            Text(text = "${dataTransfer.telefono}", color = color_fontbtn)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = "Banco:  ", color = color_fontbtn, fontWeight = FontWeight.Bold)
            Text(text = "${dataTransfer.banco}", color = color_fontbtn)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = "Ref:  ", color = color_fontbtn, fontWeight = FontWeight.Bold)
            Text(text = "00000000011122233", color = color_fontbtn)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "MONTO:  ",
                fontSize = 18.sp,
                color = color_fontbtn,
                fontWeight = FontWeight.Bold
            )
            Text(text = "Bs. ${dataTransfer.monto}", fontSize = 18.sp, color = color_fontbtn)
        }


        if(status) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Exito",
                    fontSize = 18.sp,
                    color = color_success,
                    fontWeight = FontWeight.Bold
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    modifier = Modifier.fillMaxSize(0.3F),
                    tint= color_success,
                    painter =painterResource(id = R.drawable.ic_chech_ok),
                    contentDescription = "Localized description"
                )

            }
        } else {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "NEGADA, Error ******",
                    fontSize = 18.sp,
                    color = color_danger,
                    fontWeight = FontWeight.Bold
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    modifier = Modifier.fillMaxSize(0.5F),
                    tint= color_danger,
                    painter = painterResource(id = R.drawable.ic_circle_error),
                    contentDescription = "Localized description"
                )

            }
        }

    }

}

@Preview(showBackground = false, heightDp = 333, widthDp = 500 )
@Composable
fun getPreview() {
    cardsAlert(title = "Recibo Confirmado")

}
