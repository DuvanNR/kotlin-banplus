package com.example.banplus.navigation

sealed class PathRouter (val route: String) {
    object VueltoRoute: PathRouter("vuelto")
    object VueltoNextRoute: PathRouter("vueltoNext")
    object ReporteRoute: PathRouter("reports")
    object HomeRoute: PathRouter("home")
    object ConfirTrancation: PathRouter("confirtrancation")
    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
