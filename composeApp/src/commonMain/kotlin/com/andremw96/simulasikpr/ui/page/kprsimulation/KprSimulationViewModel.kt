package com.andremw96.simulasikpr.ui.page.kprsimulation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.math.pow

expect class DecimalFormat() {
    fun format(double: Double): String
}

class KprSimulationViewModel(
    private val decimalFormat: DecimalFormat,
) : ViewModel(), KprSimulationPageAction {
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
            decimalFormat.format((currencyValue / housePriceValue) * 100.0)
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
            decimalFormat.format(((percentageValue / 100.0) * housePriceValue))
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

    override fun calculateSimulation() {
        val housePriceValue = _uiState.value.housePrice.toDoubleOrNull() ?: 0.0
        val downPaymentCurrencyValue = _uiState.value.downPaymentCurrency.toDoubleOrNull() ?: 0.0
        val tenorValue = _uiState.value.tenor.toIntOrNull() ?: 0
        val interestsValue = _uiState.value.interests.map { it.toDoubleOrNull() ?: 0.0 }

        val totalMonths = tenorValue * 12
        val monthlyInterestRates = interestsValue.map { it / 12 / 100 }
        var remainingLoan = housePriceValue - downPaymentCurrencyValue
        var currentMonth = 1

        println("Bulan | Sisa Pokok Awal | Cicilan Bunga | Cicilan Pokok | Cicilan Total | Sisa Pokok Akhir")
        println("--------------------------------------------------------------------------------------------")

        for (year in 0 until tenorValue) {
            val monthlyInterestRate = monthlyInterestRates[year]
            val monthsInYear = 12
            val remainingMonths = totalMonths - currentMonth + 1

            val monthlyInstallment =
                (remainingLoan * monthlyInterestRate * (1 + monthlyInterestRate).pow(remainingMonths.toDouble())) /
                        ((1 + monthlyInterestRate).pow(remainingMonths.toDouble()) - 1)

            for (month in 1..monthsInYear) {
                if (currentMonth > totalMonths) break

                val interestPayment = remainingLoan * monthlyInterestRate
                val principalPayment = monthlyInstallment - interestPayment
                val remainingLoanEnd = remainingLoan - principalPayment

                println(
                    "$currentMonth | ${decimalFormat.format(remainingLoan)} | ${
                        decimalFormat.format(
                            interestPayment
                        )
                    } | ${decimalFormat.format(principalPayment)} | ${
                        decimalFormat.format(
                            monthlyInstallment
                        )
                    } | ${decimalFormat.format(remainingLoanEnd)}"
                )

                remainingLoan = remainingLoanEnd
                currentMonth++
            }
        }
    }
}