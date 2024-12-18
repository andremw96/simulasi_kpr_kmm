package com.andremw96.simulasikpr

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.andremw96.simulasikpr.ui.page.kprsimulation.DecimalFormat
import com.andremw96.simulasikpr.ui.page.kprsimulation.KprSimulationPage
import com.andremw96.simulasikpr.ui.page.kprsimulation.KprSimulationViewModel
import com.andremw96.simulasikpr.ui.theme.KprSimTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    val viewModel = KprSimulationViewModel(DecimalFormat())
    val viewState by viewModel.uiState.collectAsState()

    KprSimTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            KprSimulationPage(viewState, viewModel)
        }
    }
}