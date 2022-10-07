package com.example.banplus.component.template

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.banplus._interface.iCommerce
import com.example.banplus.ui.theme.color_black
import com.example.banplus.ui.theme.color_white

@Composable
fun CardComponent(title: String, header: Boolean = false, children: @Composable() () -> Unit) {
    val context = LocalContext.current
    val commerce = iCommerce.getCommerce(context)
    Card(
        modifier = Modifier.padding(horizontal = 24.dp),
        border = BorderStroke(1.dp, color_black),
        shape = RoundedCornerShape(0.dp),
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                text = title,
                color = color_white,
                modifier = Modifier
                    .background(color_black)
                    .fillMaxWidth()
                    .padding(vertical = 9.dp),
                textAlign = TextAlign.Center
            )

            if (header) {
                Text(
                    text = "${commerce!!.razonSocial} \n RIF: ${commerce!!.tipo.capitalize()}-${commerce!!.rif}",
                    color = color_black,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 41.dp),
                    textAlign = TextAlign.Center
                )


            }
            children()
        }
    }
}
