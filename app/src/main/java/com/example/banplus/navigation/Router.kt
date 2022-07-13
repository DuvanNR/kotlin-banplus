package com.example.banplus.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.banplus.views.ViewInit
import com.example.banplus.views.ViewReportes
import com.example.banplus.views.ViewVuelto

@Composable
fun Router() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination =PathRouter.HomeRoute.route) {
        composable(route = PathRouter.ReporteRoute.route) {
           ViewReportes(navController)
        }
        composable(route = PathRouter.VueltoRoute.route) {
            ViewVuelto(navController)
        }
        composable(route = PathRouter.HomeRoute.route) {
            ViewInit(navController)
        }
    }

}