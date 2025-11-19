package com.nadiaguerra.scores_unidad3.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nadiaguerra.scores_unidad3.presentation.views.AddView
import com.nadiaguerra.scores_unidad3.presentation.views.DashboardView
import com.nadiaguerra.scores_unidad3.presentation.views.EditView

@Composable
fun NavController(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "Dashboard"
    ){
        composable("Dashboard") {
            DashboardView(navController)
        }

        composable("Add") {
            AddView(navController)
        }

        composable(
            route = "Edit/{studentId}",
            arguments = listOf(navArgument("studentId") { type = NavType.IntType })
        ) { backStackEntry ->
            val studentId = backStackEntry.arguments?.getInt("studentId") ?: 0
            EditView(navController, studentId)
        }

        //no puede ser asi pq es necesario pasarle un id especifico
       // composable("Edit") {
        //    EditView(navController)
        //}
    }
}