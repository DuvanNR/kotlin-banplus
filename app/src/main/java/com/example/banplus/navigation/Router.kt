package com.example.banplus.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.banplus.api.ApiResponseStatus
import com.example.banplus.api.vuelto.response.Tranferp2pResponse
import com.example.banplus.views.ViewInit
import com.example.banplus.views.ViewReportes
import com.example.banplus.views.ViewVuelto
import com.example.banplus.views.vueltoNextForm

@Composable
fun Router(status: ApiResponseStatus<Tranferp2pResponse.Pago>? = null, onClick:(cedula: String,
           cell: String,
           banco: String,
           tipo: String,
           monto:String) -> Unit ){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination =PathRouter.HomeRoute.route) {
        composable(route = PathRouter.ReporteRoute.route) {
           ViewReportes(navController)
        }
        composable(
            route = PathRouter.VueltoNextRoute.route + "/{cell}/{tipo}/{cedula}",
            arguments = listOf(
                navArgument("cell") {
                    type = NavType.StringType
                    nullable = false
                    defaultValue = "11" },
                navArgument("tipo") {
                    type = NavType.StringType
                    nullable = false
                    defaultValue = "11" },
                navArgument("cedula") {
                    type = NavType.StringType
                    nullable = false
                    defaultValue = "11" }
            )
        ) {
            val cell = it.arguments?.getString("cell")
            val tipo = it.arguments?.getString("tipo")
            val cedula = it.arguments?.getString("cedula")
            vueltoNextForm(navController,cell,cedula,tipo, status, onClick=onClick)
        }
        composable(route = PathRouter.VueltoRoute.route) {
            ViewVuelto(navController)
        }
        composable(route = PathRouter.HomeRoute.route) {
            ViewInit(navController)
        }
    }

}