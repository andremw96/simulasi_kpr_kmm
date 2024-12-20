package com.andremw96.simulasikpr.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.andremw96.simulasikpr.ui.page.kprsimulation.KprSimulationPage
import com.andremw96.simulasikpr.ui.page.kprsimulation.KprSimulationViewModel
import com.andremw96.simulasikpr.ui.page.kprsimulationresult.KprSimulationResultPage

// Screen.kt
sealed class KprSimScreen(val route: String) {
    data object KprSimulationPage : KprSimScreen("kpr_simulation_page_screen")
    data object KprSimulationResultPage : KprSimScreen("kpr_simulation_result_page_screen")
}

@Composable
fun KprSimNavigationHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val viewModel: KprSimulationViewModel = remember { KprSimulationViewModel() }
    val viewState by viewModel.uiState.collectAsState()

    NavHost(
        navController = navController,
        startDestination = KprSimScreen.KprSimulationPage.route
    ) {
        composable(route = KprSimScreen.KprSimulationPage.route) {
            KprSimulationPage(viewState, viewModel) {
                navController.navigate(KprSimScreen.KprSimulationResultPage.route)
            }
        }
        composable(
            route = KprSimScreen.KprSimulationResultPage.route,
        ) {
            KprSimulationResultPage(viewState)
        }
    }

}