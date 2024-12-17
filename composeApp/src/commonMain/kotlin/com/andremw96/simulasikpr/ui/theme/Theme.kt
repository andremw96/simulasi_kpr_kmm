package com.andremw96.simulasikpr.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.unit.dp
import com.andremw96.simulasikpr.ui.theme.color.KprSimColor
import com.andremw96.simulasikpr.ui.theme.color.LocalKprSimColor
import com.andremw96.simulasikpr.ui.theme.color.kprSimColorDark
import com.andremw96.simulasikpr.ui.theme.color.kprSimColorLight

object SimulasiKPRColor {
    val colors: KprSimColor
        @Composable get() = LocalKprSimColor.current
}

@Composable
fun KprSimTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    content: @Composable () -> Unit
) {
    val colorScheme = when {
//        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//            val context = LocalContext.current
//            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//        }

        darkTheme -> kprSimColorDark
        else -> kprSimColorLight
    }

    CompositionLocalProvider(
        LocalKprSimColor provides colorScheme,
    ) {
        MaterialTheme(
            typography = KprSimTypography(),
            shapes = myShapes,
            content = content
        )
    }
}

val myShapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(4.dp),
    large = RoundedCornerShape(0.dp)
)