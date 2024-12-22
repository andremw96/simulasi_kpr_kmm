package com.andremw96.simulasikpr.ui.page.kprsimulation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.andremw96.simulasikpr.ui.theme.KprSimTypography
import com.andremw96.simulasikpr.ui.theme.SimulasiKPRColor
import com.andremw96.simulasikpr.ui.widget.KprSimNumberTextField
import com.andremw96.simulasikpr.ui.widget.KprSimTopAppBar
import org.jetbrains.compose.resources.stringResource
import simulasikpr.composeapp.generated.resources.Res
import simulasikpr.composeapp.generated.resources.string_bunga_per_tahun
import simulasikpr.composeapp.generated.resources.string_dp
import simulasikpr.composeapp.generated.resources.string_dp_percentage
import simulasikpr.composeapp.generated.resources.string_harga_rumah
import simulasikpr.composeapp.generated.resources.string_hitung
import simulasikpr.composeapp.generated.resources.string_tahun_ke
import simulasikpr.composeapp.generated.resources.string_tenor

@Composable
fun KprSimulationPage(
    viewState: KprSimulationPageState,
    action: KprSimulationPageAction,
    onSimulationResult: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(SimulasiKPRColor.colors.kprSimulationPageColors.backgroundColor)
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        KprSimTopAppBar()

        KprSimulationPageContent(
            housePrice = viewState.housePrice,
            onHousePriceValueChange = { action.updateHousePrice(it) },
            downPaymentCurrency = viewState.downPaymentCurrency,
            onDownPaymentCurrencyChange = { action.updateDownPaymentCurrency(it) },
            downPaymentPercentage = viewState.downPaymentPercentage,
            onDownPaymentPercentageChange = { action.updateDownPaymentPercentage(it) },
            tenor = viewState.tenor,
            onTenorChange = {
                action.updateTenor(it)
            },
            interests = viewState.interests,
            onUpdateInterest = { index, newValue -> action.updateInterest(index, newValue) },
            onSubmit = {
                action.calculateSimulation()
                onSimulationResult()
            }
        )
    }
}

@Composable
fun KprSimulationPageContent(
    modifier: Modifier = Modifier,
    housePrice: String,
    onHousePriceValueChange: (String) -> Unit,
    downPaymentCurrency: String,
    onDownPaymentCurrencyChange: (String) -> Unit,
    downPaymentPercentage: String,
    onDownPaymentPercentageChange: (String) -> Unit,
    tenor: String,
    onTenorChange: (String) -> Unit,
    interests: MutableList<String>,
    onUpdateInterest: (Int, String) -> Unit,
    onSubmit: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        KprSimNumberTextField(
            value = housePrice,
            onValueChange = onHousePriceValueChange,
            label = stringResource(Res.string.string_harga_rumah),
            modifier = Modifier.fillMaxWidth(),
        )

        if (housePrice.isNotBlank()) {
            Text(
                text = housePrice.toDouble().toIdrCurrency(),
                style = KprSimTypography().bodyMedium,
                color = SimulasiKPRColor.colors.kprSimulationPageColors.housePriceTextColor,
                modifier = Modifier.align(Alignment.Start).padding(top = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            KprSimNumberTextField(
                value = downPaymentCurrency,
                onValueChange = onDownPaymentCurrencyChange,
                label = stringResource(Res.string.string_dp),
                isEnabled = housePrice.isNotBlank() && housePrice != "0.00",
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            )

            Row(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                KprSimNumberTextField(
                    value = downPaymentPercentage,
                    onValueChange = onDownPaymentPercentageChange,
                    label = stringResource(Res.string.string_dp_percentage),
                    isEnabled = housePrice.isNotBlank() && housePrice != "0.00",
                    modifier = Modifier.weight(0.8f)
                )

                Text("%", modifier = Modifier.weight(0.2f))
            }
        }

        if (downPaymentCurrency.isNotBlank()) {
            Text(
                text = downPaymentCurrency.toDouble().toIdrCurrency(),
                style = KprSimTypography().bodyMedium,
                color = SimulasiKPRColor.colors.kprSimulationPageColors.housePriceTextColor,
                modifier = Modifier.align(Alignment.Start).padding(top = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        KprSimNumberTextField(
            value = tenor,
            onValueChange = onTenorChange,
            label = stringResource(Res.string.string_tenor),
            isEnabled = housePrice.isNotBlank() && housePrice != "0.00",
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(8.dp))

        if (tenor.toIntOrNull() != null) {
            Text(
                text = stringResource(Res.string.string_bunga_per_tahun),
                style = KprSimTypography().titleMedium,
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(2.dp))

            for (i in 1..tenor.toInt()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    KprSimNumberTextField(
                        value = if (i <= interests.size) interests[i - 1] else "",
                        onValueChange = { onUpdateInterest(i - 1, it) },
                        label = stringResource(Res.string.string_tahun_ke, i),
                        modifier = Modifier.weight(0.7f),
                    )

                    if (i != 1) {
                        Spacer(modifier = Modifier.width(4.dp))

                        IconButton(
                            onClick = { onUpdateInterest(i-1, interests[i-2]) },
                            modifier = Modifier.wrapContentHeight()
                                .weight(0.1f),
                        ) {
                            Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription = "Dropdown")
                        }
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onSubmit,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonColors(
                containerColor = SimulasiKPRColor.colors.kprSimulationPageColors.submitButtonBgColor,
                contentColor = SimulasiKPRColor.colors.kprSimulationPageColors.submitButtonTextColor,
                disabledContentColor = SimulasiKPRColor.colors.kprSimulationPageColors.submitButtonTextColor,
                disabledContainerColor = SimulasiKPRColor.colors.kprSimulationPageColors.submitButtonDisabledBgColor,
            ),
            shape = RoundedCornerShape(8.dp),
            enabled = housePrice.isNotBlank() && housePrice != "0.00" && tenor.isNotBlank() && tenor != "0",
        ) {
            Text(
                text = stringResource(Res.string.string_hitung),
                style = KprSimTypography().bodyMedium,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}