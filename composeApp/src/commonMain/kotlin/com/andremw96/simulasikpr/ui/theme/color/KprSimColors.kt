package com.andremw96.simulasikpr.ui.theme.color

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val primaryColor: Color = Color(0xFF6bc201)
val secondaryColor: Color = Color(0xFFF8FAF4)
val accentColor: Color = Color(0xFF9D8B01)
val white: Color = Color.White
val black: Color = Color.Black
val gray: Color = Color.Gray
val lightGray: Color = Color.LightGray

class KprSimColor(
    val kprSimTopAppBarColor: KprSimTopAppBarColor,
    val kprSimulationPageColors: KprSimulationPageColors,
)

val LocalKprSimColor = staticCompositionLocalOf {
    kprSimColorLight
}

val kprSimColorDark = KprSimColor(
    kprSimTopAppBarDark,
    kprSimulationPagDark,
)

val kprSimColorLight = KprSimColor(
    kprSimTopAppBarLight,
    kprSimulationPageLight,
)