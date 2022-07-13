package com.example.banplus.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ViewVuelto() {
    Scaffold() {
      BodyContent()  
    }

}

@Composable
fun BodyContent() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(text = "Hola Navegacion")
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Navergar")
            
        }
        
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ViewVuelto()
}
