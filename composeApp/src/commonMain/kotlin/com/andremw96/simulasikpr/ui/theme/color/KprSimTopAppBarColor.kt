package com.andremw96.simulasikpr.ui.theme.color

import androidx.compose.ui.graphics.Color

class KprSimTopAppBarColor(
    val backgroundColor: Color,
    val textColor: Color,
)

val kprSimTopAppBarLight = KprSimTopAppBarColor(
    backgroundColor = white,
    textColor = primaryColor,
)

val kprSimTopAppBarDark = KprSimTopAppBarColor(
    backgroundColor = gray,
    textColor = white,
)