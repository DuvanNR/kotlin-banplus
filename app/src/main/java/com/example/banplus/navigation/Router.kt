package com.example.banplus.navigation
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.banplus._interface.iTransaction
import com.example.banplus.db.schema.Commerce
import com.example.banplus.db.schema.TransCount
import com.example.banplus.db.schema.Transaction
import com.example.banplus.views.*

@Composable
fun Router(
    onEventTransction: (iData: iTransaction, String) -> Unit,
    onGoToReportes: () -> Unit,
    onPrintDetails: (TransCount, Commerce) -> Unit,
    checkForInternet:(aa: NavController, bb: String) -> Unit,
    IrEditTerminal:() -> Unit = {},
    title_view:(status:String) -> Unit
    ) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = PathRouter.HomeRoute.route) {
        composable(route = PathRouter.ReporteRoute.route) {

            title_view("vuelto")
            ViewReportes(onGoToReportes = { onGoToReportes() }, onPrintDetails = onPrintDetails)
        }
        composable(
            route = PathRouter.VueltoNextRoute.route + "/{tipo}/{cedula}/{cell}",
            arguments = getList()
        ) {
            val cell = it.arguments?.getString("cell")
            val tipo = it.arguments?.getString("tipo")
            val cedula = it.arguments?.getString("cedula")

            title_view("vuelto")
            vueltoNextForm(
                navController,
                iTransaction(tipo = "$tipo", cedula = "$cedula", "$cell")
            )
        }
        composable(route = PathRouter.VueltoRoute.route) {
            title_view("vuelto")
            ViewVuelto(navController)
        }
        composable(route = PathRouter.HomeRoute.route) {
            ViewInit( IrEditTerminal = IrEditTerminal, checkForInternet={
                checkForInternet(navController, it)
            })
        }


        composable(route = getPath(), arguments = getList()) {
            val cell = it.arguments?.getString("cell")
            val tipo = it.arguments?.getString("tipo")
            val cedula = it.arguments?.getString("cedula")
            val monto = it.arguments?.getString("monto")
            val banco = it.arguments?.getString("banco")
            val nameBanco = it.arguments?.getString("nameBanco")

            title_view("")
            ConfirmarteTransaction(
                navController = navController,
                resp = iTransaction("$tipo", "$cedula", "$cell", "$banco", "$monto", "$nameBanco"),
                onEventExito = { it ->
                    onEventTransction(it, "true")
                },
                onEventError = {
                    onEventTransction(it, "false")
                }

            )
        }

    }

}


fun getPath(): String {
    return PathRouter.ConfirTrancation.route + "/{tipo}/{cedula}/{cell}/{banco}/{monto}/{nameBanco}"
}

fun getList(): List<NamedNavArgument> {
    return listOf(
        navArgument("cell") {
            type = NavType.StringType
            nullable = false
            defaultValue = "11"
        },
        navArgument("tipo") {
            type = NavType.StringType
            nullable = false
            defaultValue = "11"
        },
        navArgument("cedula") {
            type = NavType.StringType
            nullable = false
            defaultValue = "11"
        },
        navArgument("banco") {
            type = NavType.StringType
            nullable = false
            defaultValue = "11"
        },
        navArgument("monto") {
            type = NavType.StringType
            nullable = false
            defaultValue = "11"
        },
        navArgument("nameBanco") {
            type = NavType.StringType
            nullable = false
            defaultValue = "11"
        }

    )
}