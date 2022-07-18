import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.banplus.ResListA
import com.example.banplus._interface.iTransaction
import com.example.banplus.navigation.PathRouter
import com.example.banplus.viewmodel.VueltoViewModel
import com.example.banplus.views.*

@Composable
fun Router(viewModelVuelto: VueltoViewModel, onEventTransction: (iData: iTransaction) -> Unit) {
    val navController = rememberNavController()
    val status = viewModelVuelto.status
    NavHost(navController = navController, startDestination = PathRouter.HomeRoute.route) {
        composable(route = PathRouter.ReporteRoute.route) {
            ViewReportes(navController)
        }
        composable(
            route = PathRouter.VueltoNextRoute.route + "/{tipo}/{cedula}/{cell}",
            arguments = getList()
        ) {
            val cell = it.arguments?.getString("cell")
            val tipo = it.arguments?.getString("tipo")
            val cedula = it.arguments?.getString("cedula")
            vueltoNextForm(navController, cell, cedula, tipo)
        }
        composable(route = PathRouter.VueltoRoute.route) {
            ViewVuelto(navController)
        }
        composable(route = PathRouter.HomeRoute.route) {
            ViewInit(navController)
        }
        composable(route = PathRouter.ListReport.route) {
            listReportView(navController, ResListA)
        }

        composable(route = getPath(), arguments = getList() ) {
            val cell = it.arguments?.getString("cell")
            val tipo = it.arguments?.getString("tipo")
            val cedula = it.arguments?.getString("cedula")
            val monto = it.arguments?.getString("monto")
            val banco = it.arguments?.getString("banco")
            ConfirmarteTransaction(
                navController = navController,
                resp = iTransaction("$tipo", "$cedula", "$cell", "$banco", "$monto"),
                viewModelVuelto =viewModelVuelto,
                status = status.value,
                onErrorDialog = {
                    viewModelVuelto.onResetApiResponse()
                    navController.navigate(PathRouter.HomeRoute.route)
                },
                onEventExito = {
                    tipo ,cedula ,telefono,banco, monto ->
                    onEventTransction(iTransaction(tipo = tipo, cedula = cedula, telefono= telefono,banco= banco, monto=monto))
                }

            )
        }

    }

}
fun getPath():String {
   return PathRouter.ConfirTrancation.route + "/{tipo}/{cedula}/{cell}/{banco}/{monto}"
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
        }

    )
}