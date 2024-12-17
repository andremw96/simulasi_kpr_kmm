package com.andremw96.simulasikpr.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.andremw96.simulasikpr.ui.theme.KprSimTypography
import com.andremw96.simulasikpr.ui.theme.SimulasiKPRColor
import org.jetbrains.compose.resources.stringResource
import simulasikpr.composeapp.generated.resources.Res
import simulasikpr.composeapp.generated.resources.app_name

@Composable
fun KprSimTopAppBar(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(SimulasiKPRColor.colors.kprSimTopAppBarColor.backgroundColor)
            .padding(16.dp),
    ) {
        Text(
            text = stringResource(Res.string.app_name),
            color = SimulasiKPRColor.colors.kprSimTopAppBarColor.textColor,
            style = KprSimTypography().titleLarge,
        )
    }

    HorizontalDivider(
        color = SimulasiKPRColor.colors.kprSimulationPageColors.dividerColor,
        thickness = 2.dp,
        modifier = Modifier.shadow(4.dp)
    )
}