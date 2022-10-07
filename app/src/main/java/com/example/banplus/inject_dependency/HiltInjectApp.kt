package com.example.banplus.inject_dependency

import android.app.Application
import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import com.nexgo.oaf.apiv3.APIProxy
import com.nexgo.oaf.apiv3.DeviceEngine
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HiltInjectApp() : Application(), Parcelable {
    var deviceEngine: DeviceEngine? = null

    constructor(parcel: Parcel) : this() {}

    override fun writeToParcel(parcel: Parcel, flags: Int) {}

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<HiltInjectApp> {
        override fun createFromParcel(parcel: Parcel): HiltInjectApp = HiltInjectApp(parcel)

        override fun newArray(size: Int): Array<HiltInjectApp?> = arrayOfNulls(size)
    }

    override fun onCreate() {
        super.onCreate()
        if (obtenerNombreDeDispositivo() != "Samsung SM-A217M") {
            deviceEngine =
                APIProxy.getDeviceEngine(this)  //TODO: Al hacer las pruebas en el N3 descomentar esta linea
            deviceEngine?.getEmvHandler("app1")
        }
    }

    fun obtenerNombreDeDispositivo(): String {
        var fabricante: String = Build.MANUFACTURER;
        var modelo: String = Build.MODEL;
        if (modelo.startsWith(fabricante)) {
            return primeraLetraMayuscula(modelo);
        } else {
            return primeraLetraMayuscula(fabricante) + " " + modelo;
        }
    }

    private fun primeraLetraMayuscula(cadena: String): String {
        if (cadena == null || cadena.length == 0) {
            return ""
        }
        val primeraLetra = cadena[0]
        return if (Character.isUpperCase(primeraLetra)) {
            cadena
        } else {
            primeraLetra.uppercaseChar().toString() + cadena.substring(1)
        }
    }
}

