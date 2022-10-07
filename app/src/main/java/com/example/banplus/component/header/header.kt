package com.example.banplus.component.header

import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.banplus.R
import com.example.banplus.context.NavTabContext
import com.example.banplus.ui.theme.color_primary
import com.example.banplus.ui.theme.color_primary1
import com.example.banplus.utils.GoToActivity
import com.example.banplus.utils.iNameActivity
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.concurrent.schedule

@Composable
fun HeaderInit(
    iconBoolean: Boolean, menu: Boolean
) {
    val context = LocalContext.current
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            color_primary1, color_primary
                        )
                    )
                ), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.Top
        ) {
            Logo(icon = if (iconBoolean) R.drawable.ic_banplus_vector else R.drawable.ic_recurso_4)
        }
        Box(
            modifier = Modifier.fillMaxWidth(),
        ) {

            if (menu) {
                var DateNow: String = ""
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    val current = LocalDateTime.now()
                    val formatter = DateTimeFormatter.ofPattern("EEEE dd LLLL yyyy hh:mm:ss a")
                    DateNow = current.format(formatter)

                } else {
                    var date = Date()
                    val formatter = SimpleDateFormat("EEEE dd LLLL yyyy hh:mm:ss a")
                    DateNow = formatter.format(date)
                }
                Text(
                    text = DateNow.toString(), modifier = Modifier.align(Alignment.Center),
                    fontSize = 10.sp,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold,
                )
                TextButton(modifier = Modifier.align(Alignment.CenterEnd), onClick = {
                    NavTabContext.resetNumTab()
                    Timer().schedule(1000) {
                        GoToActivity(iNameActivity.SETTING, context)

                    }

                }) {
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