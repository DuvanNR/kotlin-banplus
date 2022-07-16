package com.example.banplus.component.header

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.banplus.R

@Composable
fun Logo() {
    Image(
        painterResource(id = R.drawable.ic_banplus_vector),
        contentDescription = "",
        modifier = Modifier.size(
            width = 200.dp,
            height = 150.dp
        )
    )
}