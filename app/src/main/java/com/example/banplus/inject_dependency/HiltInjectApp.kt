package com.example.banplus.inject_dependency

import android.app.Application
import android.os.Parcel
import android.os.Parcelable
import com.nexgo.oaf.apiv3.APIProxy
import com.nexgo.oaf.apiv3.DeviceEngine
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HiltInjectApp() : Application(), Parcelable {
    var deviceEngine: DeviceEngine? = null

    constructor(parcel: Parcel) : this() {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {}

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<HiltInjectApp> {
        override fun createFromParcel(parcel: Parcel): HiltInjectApp = HiltInjectApp(parcel)

        override fun newArray(size: Int): Array<HiltInjectApp?> = arrayOfNulls(size)
    }

    override fun onCreate() {
        super.onCreate()
        deviceEngine = APIProxy.getDeviceEngine(this)  //TODO: // Al hacer las pruebas en el N3 descomentar esta linea
        deviceEngine?.getEmvHandler("app1")
    }
}
