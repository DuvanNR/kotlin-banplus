package com.example.banplus.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.example.banplus.*

enum class iNameActivity {
    MAIN, REPORT, LIST_REPORT, VUELTO, SETTING, CARD
}

fun GoToActivity(nameactivity: iNameActivity, context: Context, finish: Boolean = false) {
    val activity = (context as Activity)
    when (nameactivity) {
        iNameActivity.MAIN -> {
            val intent = Intent(activity, MainActivity::class.java)
            intent.putExtra("Activity", "Main")
            context.startActivity(intent)
        }
        iNameActivity.REPORT -> {
            val intent = Intent(activity, ReportActivity::class.java)
            intent.putExtra("Activity", "Main")
            context.startActivity(intent)
        }
        iNameActivity.VUELTO -> {
            val intent = Intent(activity, VueltoActivity::class.java)
            intent.putExtra("Activity", "Vuelto")
            context.startActivity(intent)
        }
        iNameActivity.SETTING -> {
            val intent = Intent(activity, SettingActivity::class.java)
            intent.putExtra("Activity", "Vuelto")
            context.startActivity(intent)
        }
        iNameActivity.LIST_REPORT -> {
            val intent = Intent(activity, ListReportActivity::class.java)
            intent.putExtra("Activity", "Vuelto")
            context.startActivity(intent)
        }
        iNameActivity.CARD -> {
            val intent = Intent(activity, CardActivity::class.java)
            intent.putExtra("Activity", "Vuelto")
            context.startActivity(intent)
        }
    }
    if (finish) {
        activity?.finish()
    }
}