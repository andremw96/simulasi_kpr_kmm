package com.andremw96.simulasikpr.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.Font
import simulasikpr.composeapp.generated.resources.Res
import simulasikpr.composeapp.generated.resources.itim_regular

@Composable
fun KprSimFontFamily() = FontFamily(
    Font(Res.font.itim_regular, FontWeight.Normal, FontStyle.Normal)
)

@Composable
fun KprSimTypography() = Typography().run {
    val fontFamily = KprSimFontFamily()

    Typography().copy(
        headlineLarge = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            lineHeight = 28.sp,
            letterSpacing = 0.sp
        ),
        headlineMedium = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.sp
        ),
        labelSmall = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.sp
        ),
        bodyLarge = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp,
            lineHeight = 28.sp,
            letterSpacing = 0.5.sp
        ),
        bodyMedium = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.5.sp
        )
    )
}

