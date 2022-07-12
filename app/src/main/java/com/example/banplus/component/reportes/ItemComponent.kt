package com.example.banplus.component.reportes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.banplus.R

class Dog(
    val id: Long,
    val index: Int,
    val name: String,
    val type: String,
    val heightFemale: String,
    val heightMale: String,
    val imageUrl: String,
    val lifeExpectancy: String,
    val temperament: String,
    val weightFemale: String,
    val weightMale: String
)

@Composable
fun ItemComponent() {
    Box(
        modifier = Modifier
            .background(colorResource(id = R.color.teal_200))
            .padding(start = 8.dp, end = 8.dp, bottom = 16.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        val dog = Dog(
            1,
            1,
            "text1",
            "tpe",
            "heightFemale",
            "heightMale",
            "http://",
            "hasd",
            "ksdf",
            "ksdjf",
            "kdfs",
        )
        DogInformation(dog)
    }
}

@Composable
fun DogInformation(dog: Dog) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 180.dp)
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(4.dp),
            color = colorResource(id = R.color.white)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(R.string.dog_index_format, dog.index),
                    fontSize = 32.sp,
                    color= colorResource(R.color.black),
                    textAlign = TextAlign.End
                )

            }
        }

    }

}


@Preview()
@Composable()
fun showPreview() {
    ItemComponent()
}
