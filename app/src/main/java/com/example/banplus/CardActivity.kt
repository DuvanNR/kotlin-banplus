package com.example.banplus

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.banplus.template.ResponseTemplate
import com.example.banplus.ui.theme.BanplusTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideSystemUI()
        setContent {
            ResponseTemplate()
        }

    }

    private fun hideSystemUI() {
        val decorView = window.decorView
        decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent): Boolean {
        super.onKeyUp(keyCode, event)
        hideSystemUI()
        return true

    }
}
//    @Composable
//    fun Greeting(name: String) {
//        Text(text = "Hello $name!")
//    }
//
//    @Preview(showBackground = true)
//    @Composable
//    fun DefaultPreview2() {
//        BanplusTheme {
//            Greeting("Android")
//        }
//    }