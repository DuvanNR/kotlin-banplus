package com.example.banplus.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.banplus.R
import com.example.banplus.ui.theme.color_black
import com.example.banplus.ui.theme.color_white

@Composable
fun BtnNext(ico: Painter, text: String, background: Color = color_black, colorText: Color = color_white, onClick: () -> Unit?, modifier: Modifier = Modifier, enabled: Boolean = true) {
    Button(
        modifier = modifier,
        enabled = enabled,
        shape = RoundedCornerShape(55),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = background,
            contentColor =colorText
        ),
        onClick = { onClick()},
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = text)
            Icon(painter = ico, contentDescription = stringResource(id = R.string.defualt_message_img))
        }


    }
}

@Preview(showBackground = true, widthDp = 250, heightDp = 100)
@Composable
fun previewBtna() {
    val ico = painterResource(id = R.drawable.ic_next)
    BtnNext(text="hola Mundo",onClick = {/* TODO: */}, ico = ico)
}