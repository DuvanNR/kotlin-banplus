package com.example.banplus.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.banplus.R
import com.example.banplus._interface.iTransaction
import com.example.banplus.db.schema.Commerce
import com.example.banplus.db.schema.TransCount
import com.example.banplus.ui.theme.*
import com.example.banplus.utils.ConverString
import com.example.banplus.utils.getDatetime
import com.example.banplus.viewmodel.CommerceViewModel
import java.text.DecimalFormat
import java.util.*

@Composable
fun cardsAlert(
    rif: Boolean = true,
    title: String = "Consolidado Diario",
    status: Boolean = true,
    iData: iTransaction = iTransaction(),
    viewModel: CommerceViewModel = hiltViewModel(),
    detailsData: TransCount = TransCount(0.01, 0)
) {
    val commerce = viewModel.commerce.observeAsState(Commerce("", "", "", ""))
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
                    text = "${commerce.value.razonSocial} \n RIF: ${commerce.value.tipo.capitalize()}-${commerce.value.rif}",
                    color = color_black,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 41.dp),
                    textAlign = TextAlign.Center
                )

            }
            when (title) {
                "Consolidado Diario" -> CardA(detailsData)
                "Confirmar Operación" -> CardC(iData)
                else -> CardB(status = status, dataTransfer = iData)
            }
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
            Text(text = "${iData.tipo.capitalize()}-${iData.cedula}", color = color_fontbtn)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = "Teléfono:  ", color = color_fontbtn, fontWeight = FontWeight.Bold)
            Text(text = iData.telefono, color = color_fontbtn)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = "Banco:  ", color = color_fontbtn, fontWeight = FontWeight.Bold)
            Text(text = "${iData.nameBanco}", color = color_fontbtn)
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "MONTO",
                fontSize = 20.sp,
                color = color_black,
                fontWeight = FontWeight.Bold
            )
            val forma = DecimalFormat("#,##0.00")
            Text(
                text = "Bs. ${forma.format("${iData.monto}".toFloat())}",
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
                color = color_black
            )
        }
    }
}

@Composable
fun CardA(CountTransa: TransCount) {
    val date = getDatetime()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 100.dp, start = 12.dp)

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = "Fecha:  ", color = color_fontbtn, fontWeight = FontWeight.Bold)
            Text(text = "${date.hora}", color = color_fontbtn)

        }
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Hora:  ", color = color_fontbtn, fontWeight = FontWeight.Bold)
            Text(text = "${date.fecha}", color = color_fontbtn)
        }
        Row(
            modifier = Modifier
                .padding(top = 13.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Pago Plus ", fontWeight = FontWeight.Bold)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = "Cantidad:  ", color = color_fontbtn, fontWeight = FontWeight.Bold)
            Text(text = "${CountTransa.total}", color = color_fontbtn)
        }
        Row(
            modifier = Modifier.padding(bottom = 33.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = "MONTO: ",
                color = color_fontbtn,
                fontWeight = FontWeight.Bold
            )
            val forma = DecimalFormat("#,##0.00")
            Text(

                text = "Bs. ${forma.format(CountTransa.amount)}",
                color = color_fontbtn,
            )
        }
    }
}

@Composable
fun CardB(status: Boolean, dataTransfer: iTransaction) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 87.dp, bottom = 8.dp,start = 25.dp, end = 25.dp)

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = "Fecha:  ", color = color_fontbtn,fontSize = 14.sp, fontWeight = FontWeight.Bold)
            Text(text = dataTransfer.fecha, fontSize = 14.sp,color = color_fontbtn)

        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = "Hora:  ", color = color_fontbtn, fontSize = 14.sp,fontWeight = FontWeight.Bold)
            Text(text = dataTransfer.hora, fontSize = 14.sp,color = color_fontbtn)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = "Cedula:  ", color = color_fontbtn, fontSize = 14.sp,fontWeight = FontWeight.Bold)
            Text(
                text = "${dataTransfer.tipo.capitalize()}-${ConverString("${dataTransfer.cedula}")}",
                fontSize = 14.sp,
                color = color_fontbtn
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = "Teléfono:  ", color = color_fontbtn,fontSize = 14.sp, fontWeight = FontWeight.Bold)
            Text(text = ConverString("${dataTransfer.telefono}", init = 4, fin = 3), fontSize = 14.sp,color = color_fontbtn)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = "Banco:  ", color = color_fontbtn, fontSize = 14.sp,fontWeight = FontWeight.Bold)
            Text(text = "${dataTransfer.nameBanco}", fontSize = 14.sp,color = color_fontbtn)
        }
        if (status) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(text = "Ref:  ", color = color_fontbtn, fontSize = 14.sp,fontWeight = FontWeight.Bold)

                Text(text = "${dataTransfer.ref}", fontSize = 14.sp,color = color_fontbtn)
            }
        }



        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 3.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "MONTO: ",
                color = color_black,
                fontWeight = FontWeight.Bold
            )
            val forma = DecimalFormat("#,##0.00")
            Text(
                text = "Bs. ${forma.format("${dataTransfer.monto}".toFloat())}",
                fontWeight = FontWeight.Bold,
                color = color_black
            )


        }


        if (status) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Aprobado",
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
                    modifier = Modifier.fillMaxSize(0.18F),
                    tint = color_success,
                    painter = painterResource(id = R.drawable.ic_chech_ok),
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
                    text = "NEGADA, ${dataTransfer.message}",
                    fontSize = 13.sp,
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
                    modifier = Modifier.fillMaxSize(0.18F),
                    tint = color_danger,
                    painter = painterResource(id = R.drawable.ic_circle_error),
                    contentDescription = "Localized description"
                )

            }
        }

    }

}

@Preview(showBackground = false, heightDp = 333, widthDp = 500)
@Composable
fun getPreview() {
    cardsAlert(title = "Recibo Confirmado")

}
