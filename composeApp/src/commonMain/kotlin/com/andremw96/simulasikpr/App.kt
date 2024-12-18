package com.andremw96.simulasikpr

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.andremw96.simulasikpr.ui.KprSimNavigationHost
import com.andremw96.simulasikpr.ui.theme.KprSimTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    KprSimTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            KprSimNavigationHost()
        }
    }
}