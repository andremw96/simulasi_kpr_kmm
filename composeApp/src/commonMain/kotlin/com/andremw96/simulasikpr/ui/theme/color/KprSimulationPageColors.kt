package com.andremw96.simulasikpr.ui.theme.color

import androidx.compose.ui.graphics.Color

class KprSimulationPageColors(
    val backgroundColor: Color,
    val dividerColor: Color,
    val submitButtonBgColor: Color,
    val submitButtonTextColor: Color,
    val submitButtonDisabledBgColor: Color,
    val housePriceTextColor: Color,
)

val kprSimulationPageLight = KprSimulationPageColors(
    backgroundColor = secondaryColor,
    dividerColor = lightGray,
    submitButtonBgColor = accentColor,
    submitButtonTextColor = white,
    submitButtonDisabledBgColor = gray,
    housePriceTextColor = gray,
)

val kprSimulationPagDark = KprSimulationPageColors(
    backgroundColor = gray,
    dividerColor = primaryColor,
    submitButtonBgColor = accentColor,
    submitButtonTextColor = white,
    submitButtonDisabledBgColor = gray,
    housePriceTextColor = white,
)