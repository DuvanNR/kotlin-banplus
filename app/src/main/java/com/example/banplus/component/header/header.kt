package com.example.banplus.component.header

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.banplus.ui.theme.primary
import com.example.banplus.ui.theme.primary1
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun HeaderInit() {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(

                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            primary1,
                            primary
                        )
                    )
                ),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Top
        ) {
            Logo()
        }
        Divider(
            startIndent = 332.dp
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Top
        ) {
            val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale("es", "ES"))
            val currentdate = sdf.format(Date())
            val fechaactual = sdf.parse(currentdate)
            Text(text = fechaactual.toString(), fontStyle = FontStyle.Italic)
        }

    }
}

@Preview
@Composable
fun PreviewA() {
    HeaderInit()
}