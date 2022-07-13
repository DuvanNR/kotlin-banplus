package com.example.banplus.navigation

sealed class PathRouter (val route: String) {
    object VueltoRoute: PathRouter("vuelto")
    object ReporteRoute: PathRouter("reports")
    object HomeRoute: PathRouter("home")
}
