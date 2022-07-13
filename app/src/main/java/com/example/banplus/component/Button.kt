package com.example.banplus.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.banplus.R

@Composable
fun BtnIni(ico: Painter, text: String, background: Color = Color.White, colorText: Color = Color.Black, onClick: () -> Unit?) {
    Button(
        modifier = Modifier
            .padding(6.dp)
            .height(140.dp)
            .width(140.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = background,
            contentColor =colorText
        ),
        onClick = { onClick()},
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(modifier= Modifier.fillMaxSize(0.75F), painter = ico, contentDescription = "Localized description")
            Text(text = text)
        }


    }
}

@Preview(showBackground = true)
@Composable
fun previewBtn() {
    val ico = painterResource(id = R.drawable.ic_time)
    BtnIni(text="hola Mundo",onClick = {/* TODO: */}, ico = ico)
}