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
import com.example.banplus.R
import com.example.banplus.ui.theme.color_primary
import com.example.banplus.ui.theme.color_primary1
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun HeaderInit(icon: Int = R.drawable.ic_banplus_vector) {
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