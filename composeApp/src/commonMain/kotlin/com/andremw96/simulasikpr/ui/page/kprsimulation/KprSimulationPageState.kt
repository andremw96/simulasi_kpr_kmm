package com.andremw96.simulasikpr.ui.page.kprsimulation

import com.andremw96.simulasikpr.ui.page.kprsimulation.model.SimulationResult

data class KprSimulationPageState(
    var housePrice: String = "",
    var downPaymentCurrency: String = "0",
    var downPaymentPercentage: String = "",
    var tenor: String = "",
    val interests: MutableList<String> = mutableListOf(),
    val simulationResult: List<SimulationResult> = listOf(),
)