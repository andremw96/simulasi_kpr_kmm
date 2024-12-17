package com.andremw96.simulasikpr.ui.page.kprsimulation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

expect class DecimalFormat() {
    fun format(double: Double): String
}

class KprSimulationViewModel : ViewModel(), KprSimulationPageAction {


    private val _uiState = MutableStateFlow(KprSimulationPageState())
    val uiState: StateFlow<KprSimulationPageState> = _uiState.asStateFlow()

    override fun updateHousePrice(newPrice: String) {
        _uiState.value = _uiState.value.copy(
            housePrice = newPrice,
        )
    }

    override fun updateDownPaymentCurrency(newCurrency: String) {
        val housePriceValue = _uiState.value.housePrice.toDoubleOrNull() ?: 0.0
        val currencyValue = newCurrency.toDoubleOrNull() ?: 0.0
        val newPercentage = if (housePriceValue != 0.0) {
            DecimalFormat().format((currencyValue / housePriceValue) * 100.0)
        } else {
            ""
        }
        _uiState.value = _uiState.value.copy(
            downPaymentCurrency = newCurrency,
            downPaymentPercentage = newPercentage
        )
    }

    override fun updateDownPaymentPercentage(newPercentage: String) {
        val housePriceValue = _uiState.value.housePrice.toDoubleOrNull() ?: 0.0
        val percentageValue = newPercentage.toDoubleOrNull() ?: 0.0
        val newCurrency = if (housePriceValue != 0.0) {
            DecimalFormat().format(((percentageValue / 100.0) * housePriceValue))
        } else {
            ""
        }
        _uiState.value = _uiState.value.copy(
            downPaymentPercentage = newPercentage,
            downPaymentCurrency = newCurrency
        )
    }

    override fun updateTenor(newTenor: String) {
        val tenorValue = newTenor.toIntOrNull()
        val newInterests = if (tenorValue != null) {
            List(tenorValue) { "" }
        } else {
            mutableListOf()
        }
        _uiState.value = _uiState.value.copy(
            tenor = newTenor,
            interests = newInterests.toMutableList()
        )
    }

    override fun updateInterest(index: Int, newInterest: String) {
        _uiState.value = _uiState.value.copy(
            interests = _uiState.value.interests.toMutableList().also {
                it[index] = newInterest
            }
        )
    }
}