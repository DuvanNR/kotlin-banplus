package com.example.banplus.inject_dependency

import android.app.Application
import com.nexgo.oaf.apiv3.APIProxy
import com.nexgo.oaf.apiv3.DeviceEngine
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HiltInjectApp:Application()
//    var deviceEngine: DeviceEngine? = null
//    override fun onCreate() {
//        super.onCreate()
//        deviceEngine = APIProxy.getDeviceEngine(this)
//        deviceEngine?.getEmvHandler("app1")
//    }