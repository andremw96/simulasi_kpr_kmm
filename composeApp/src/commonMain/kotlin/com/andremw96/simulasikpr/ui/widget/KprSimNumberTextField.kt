package com.andremw96.simulasikpr.ui.widget

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import com.andremw96.simulasikpr.ui.theme.KprSimTypography

@Composable
fun KprSimNumberTextField(
    value: String,
    onValueChange: (String) -> Unit,
    isCurrency: Boolean,
    isEnabled: Boolean = true,
    label: String,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(label, style = KprSimTypography().bodyMedium)
        },
        modifier = modifier,
        enabled = isEnabled,
        visualTransformation = if (isCurrency) CurrencyAmountInputVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
        textStyle = KprSimTypography().headlineMedium,
    )
}