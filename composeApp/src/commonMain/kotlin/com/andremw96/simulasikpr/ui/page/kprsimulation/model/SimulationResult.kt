package com.andremw96.simulasikpr.ui.page.kprsimulation.model

import kotlinx.serialization.Serializable

@Serializable
data class SimulationResult(
    val currentMonth: String,
    val interestRate: String,
    val interestPayment: String,
    val principalPayment: String,
    val monthlyInstallment: String,
    val remainingLoanEnd: String,
)