package com.andremw96.simulasikpr.ui.page.kprsimulation

import com.andremw96.simulasikpr.ui.page.kprsimulation.model.SimulationResult

interface KprSimulationPageAction {
    fun updateHousePrice(newPrice: String)
    fun updateDownPaymentCurrency(newCurrency: String)
    fun updateDownPaymentPercentage(newPercentage: String)
    fun updateTenor(newTenor: String)
    fun updateInterest(index: Int, newInterest: String)
    fun calculateSimulation(): List<SimulationResult>
}