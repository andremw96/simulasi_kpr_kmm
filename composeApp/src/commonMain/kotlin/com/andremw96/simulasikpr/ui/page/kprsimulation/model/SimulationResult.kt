package com.andremw96.simulasikpr.ui.page.kprsimulation.model

data class SimulationResult(
    val currentMonth: String,
    val remainingLoan: String,
    val interestPayment: String,
    val principalPayment: String,
    val monthlyInstallment: String,
    val remainingLoanEnd: String,
)