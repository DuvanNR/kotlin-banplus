package com.example.banplus.navigation

sealed class Router (val route: String) {
    object VueltoView: Router("vuelto")
    object ReportesView: Router("reports")
}
