package com.example.banplus._interface

import android.content.Context


data class iCommerce(
    var tipo: String = "",
    var razonSocial: String = "",
    var rif: String = "",
    var telefono: String = "",
    var password: String = "",
    var id: Int = 0
) {
    companion object {
        private const val PRESF_SESSION = "presfSession"
        private const val RAZONSOCIAL = "razonSocial"
        private const val RIF = "rif"
        private const val TELEFONO = "telefono"
        private const val PASSWORD = "password"
        private const val ID = "id_key"
        private const val TIPO = "tipo"
        fun setCommerce(context: Context, commerce: iCommerce) {
            context.getSharedPreferences(PRESF_SESSION, Context.MODE_PRIVATE).also {
                it.edit()
                    .putInt(ID, 1)
                    .putString(RIF, commerce.rif)
                    .putString(RAZONSOCIAL, commerce.razonSocial)
                    .putString(TELEFONO, commerce.telefono)
                    .putString(TIPO, commerce.tipo)
                    .putString(PASSWORD, commerce.password)
                    .apply()
            }

        }

        fun getCommerce(context: Context): iCommerce? {
            val prefs =
                context.getSharedPreferences(PRESF_SESSION, Context.MODE_PRIVATE) ?: return null
            val id_commerce = prefs.getInt(ID, 0)
            if (id_commerce === 0) {
                return null
            }
            return iCommerce(
                id = prefs.getInt(ID, 0) ?: 0,
                razonSocial = prefs.getString(RAZONSOCIAL, "") ?: "",
                password = prefs.getString(PASSWORD, "") ?: "",
                rif = prefs.getString(RIF, "") ?: "",
                telefono = prefs.getString(TELEFONO, "") ?: "",
                tipo = prefs.getString(TIPO, "") ?: "",
            )
        }
    }
}