package com.andremw96.simulasikpr.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.andremw96.simulasikpr.ui.theme.KprSimTypography
import com.andremw96.simulasikpr.ui.theme.SimulasiKPRColor
import org.jetbrains.compose.resources.stringResource
import simulasikpr.composeapp.generated.resources.Res
import simulasikpr.composeapp.generated.resources.app_name

@Composable
fun KprSimTopAppBar(
    modifier: Modifier = Modifier,
    backButtonOnClick: (() -> Unit)? = null,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(SimulasiKPRColor.colors.kprSimTopAppBarColor.backgroundColor)
            .padding(16.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (backButtonOnClick != null) {
                IconButton(onClick = backButtonOnClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "back",
                        tint = SimulasiKPRColor.colors.kprSimTopAppBarColor.textColor
                    )
                }
            }

            Text(
                text = stringResource(Res.string.app_name),
                color = SimulasiKPRColor.colors.kprSimTopAppBarColor.textColor,
                style = KprSimTypography().headlineLarge,
            )
        }
    }

    HorizontalDivider(
        color = SimulasiKPRColor.colors.kprSimulationPageColors.dividerColor,
        thickness = 2.dp,
        modifier = Modifier.shadow(4.dp)
    )
}