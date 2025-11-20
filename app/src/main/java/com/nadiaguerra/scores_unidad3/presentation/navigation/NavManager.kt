package com.nadiaguerra.scores_unidad3.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nadiaguerra.scores_unidad3.presentation.viewmodels.StudentViewModel
import com.nadiaguerra.scores_unidad3.presentation.views.AddView
import com.nadiaguerra.scores_unidad3.presentation.views.DashboardView
import com.nadiaguerra.scores_unidad3.presentation.views.EditView

@Composable
fun NavManager(){
    val navController = rememberNavController()
    val sharedViewModel: StudentViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "Dashboard"
    ){
        composable("Dashboard") {
            DashboardView(navController, sharedViewModel)
        }

        composable("Add") {
            AddView(navController, sharedViewModel)
        }

        composable(
            route = "Edit/{studentId}",
            arguments = listOf(navArgument("studentId") { type = NavType.IntType })
        ) { backStackEntry ->
            val studentId = backStackEntry.arguments?.getInt("studentId") ?: 0
            EditView(navController, studentId, sharedViewModel)
        }

        //no puede ser asi pq es necesario pasarle un id especifico
       // composable("Edit") {
        //    EditView(navController)
        //}
    }
}