package com.andremw96.simulasikpr.ui.page.kprsimulation

data class KprSimulationPageState(
    var housePrice: String = "",
    var downPaymentCurrency: String = "",
    var downPaymentPercentage: String = "",
    var tenor: String = "",
    val interests: MutableList<String> = mutableListOf()
)