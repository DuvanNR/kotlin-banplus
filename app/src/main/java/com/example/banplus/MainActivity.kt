package com.example.banplus

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.banplus._interface.iCommerce
import com.example.banplus.template.MainTemplate
import com.example.banplus.utils.GoToActivity
import com.example.banplus.utils.iNameActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideSystemUI()
        setContent {
            val commerce = iCommerce.getCommerce(this)
            if (commerce === null) {
                GoToActivity(iNameActivity.SETTING, this, true)
            }
            MainTemplate()
        }

    }

    private fun hideSystemUI() {
        val decorView = window.decorView
        decorView.systemUiVisibility =
            (View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent): Boolean {
        super.onKeyUp(keyCode, event)
        hideSystemUI()
        return true
    }
}
