package com.example.banplus

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.banplus.ui.theme.BanplusTheme
import com.example.banplus.viewmodel.CommerceViewModel
import com.example.banplus.views.SettingInitView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConfigPostActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val ObjectIntent: Intent = intent
        val mainActiviry = Intent(this,MainActivity::class.java )
        super.onCreate(savedInstanceState)
        setContent {
            ObjectIntent.getStringExtra("status")
            BanplusTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    SettingInitView(onRedirection = {
                        startActivity(mainActiviry)
                        finish()
                    })
                }
            }
        }
    }
}
