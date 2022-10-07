package com.example.banplus.component.template

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.banplus.R
import com.example.banplus._interface.iTransaction
import com.example.banplus.template.FormData
import com.example.banplus.ui.theme.color_black
import com.example.banplus.ui.theme.color_danger
import com.example.banplus.ui.theme.color_fontbtn
import com.example.banplus.ui.theme.color_success
import com.example.banplus.utils.ConverString
import com.example.banplus.utils.ConvertToPrice
import com.example.banplus.utils.getDatetime
import java.text.DecimalFormat


@Composable
fun CardResponse(data: iTransaction) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 87.dp, bottom = 8.dp, start = 25.dp, end = 25.dp)

    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "${stringResource(id = R.string.fecha)}:  ",
                color = color_fontbtn,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
            Text(text = data.fecha, fontSize = 14.sp, color = color_fontbtn)

        }

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "${stringResource(id = R.string.hora)}:  ",
                color = color_fontbtn,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
            Text(text = data.hora, fontSize = 14.sp, color = color_fontbtn)
        }

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "${stringResource(id = R.string.cedula)}:  ",
                color = color_fontbtn,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "${data.tipo}-${ConverString("${data.cedula}")}",
                fontSize = 14.sp,
                color = color_fontbtn
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "${stringResource(id = R.string.telefono)}:  ",
                color = color_fontbtn,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = ConverString("${data.telefono}", init = 4, fin = 3),
                fontSize = 14.sp,
                color = color_fontbtn
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "${stringResource(id = R.string.banco)}:  ",
                color = color_fontbtn,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
            Text(text = "${data.nameBanco}", fontSize = 14.sp, color = color_fontbtn)
        }
        if (data.state) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "${stringResource(id = R.string.ref)}:  ",
                    color = color_fontbtn,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(text = "${data.ref}", fontSize = 14.sp, color = color_fontbtn)
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
                text = "${stringResource(id = R.string.monto)}: ",
                color = color_black,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Bs. ${ConvertToPrice(data.monto)}",
                fontWeight = FontWeight.Bold,
                color = color_black
            )
        }


        if (data.state) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = R.string.aprobado),
                    fontSize = 18.sp,
                    color = color_success,
                    fontWeight = FontWeight.Bold
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    modifier = Modifier.fillMaxSize(0.18F),
                    tint = color_success,
                    painter = painterResource(id = R.drawable.ic_chech_ok),
                    contentDescription = stringResource(id = R.string.defualt_message_img)
                )
            }
        } else {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                val message =
                    if (data.message == "null") "Conexión Fallida" else data.message
                println("validar si este campo es null o que  $}")
                Text(
                    text = "${stringResource(id = R.string.negada)}, $message",
                    fontSize = 13.sp,
                    color = color_danger,
                    fontWeight = FontWeight.Bold
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    modifier = Modifier.fillMaxSize(0.18F),
                    tint = color_danger,
                    painter = painterResource(id = R.drawable.ic_circle_error),
                    contentDescription = stringResource(id = R.string.default_icon)
                )
            }
        }
    }
}
