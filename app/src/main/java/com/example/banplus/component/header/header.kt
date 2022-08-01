package com.example.banplus.component.header

import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.banplus.R
import com.example.banplus.ui.theme.color_primary
import com.example.banplus.ui.theme.color_primary1
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@Composable
fun HeaderInit(icon: Int = R.drawable.ic_banplus_vector, IrEditTerminal:()->Unit = {}, menu: Boolean = false) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(

                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            color_primary1,
                            color_primary
                        )
                    )
                ),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Top
        ) {
            Logo(icon = icon)
        }

        Box(
            modifier = Modifier.fillMaxWidth(),
        ) {
            var DateNow: String = ""
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val current = LocalDateTime.now()
                val formatter = DateTimeFormatter.ofPattern("EEEE dd LLLL yyyy HH:mm:ss a")
                DateNow =  current.format(formatter)

            } else {
                var date = Date()
                val formatter = SimpleDateFormat("EEEE dd LLLL yyyy HH:mm:ss a")
                DateNow = formatter.format(date)
            }
            Text(
                text = DateNow.toString(),
                modifier = Modifier.align(Alignment.Center),
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold)

            if(menu) {
                TextButton(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    onClick = IrEditTerminal
                ) {
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription = "Localized description",
                        Modifier.padding(end = 8.dp)
                    )
                }
            }


        }

    }
}

@Preview
@Composable
fun PreviewA() {
    HeaderInit()
}