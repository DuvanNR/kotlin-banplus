package com.example.banplus

import android.content.Intent
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.banplus.inject_dependency.HiltInjectApp
import com.example.banplus.ui.theme.BanplusTheme
import com.example.banplus.views.SettingInitView
import dagger.hilt.android.AndroidEntryPoint
import com.nexgo.oaf.apiv3.DeviceEngine
import com.nexgo.oaf.apiv3.DeviceInfo

@AndroidEntryPoint
class ConfigPostActivity : ComponentActivity() {
    private var deviceEngine: DeviceEngine? = null
    private var deviceInfo: DeviceInfo? = null
    override fun onCreate(savedInstanceState: Bundle?) {
       val ObjectIntent: Intent = intent
        val mainActiviry = Intent(this,MainActivity::class.java )

        super.onCreate(savedInstanceState)
        setContent {
            deviceEngine = (application as HiltInjectApp).deviceEngine
             deviceInfo = deviceEngine!!.deviceInfo
            ObjectIntent.getStringExtra("status")
            BanplusTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    SettingInitView(onRedirection = {
                        startActivity(mainActiviry)
                        finish()
                    },
                    serial = "${deviceInfo!!.sn}")
                }
            }
        }

    }

}

