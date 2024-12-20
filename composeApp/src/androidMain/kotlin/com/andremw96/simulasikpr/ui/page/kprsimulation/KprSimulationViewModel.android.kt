package com.andremw96.simulasikpr.ui.page.kprsimulation

import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

actual fun decimalFormat(double: Double): String {
    val df = java.text.DecimalFormat()
    df.isGroupingUsed = false
    df.maximumFractionDigits = 2
    df.isDecimalSeparatorAlwaysShown = false
    return df.format(double)
}
val currencySymbols = DecimalFormatSymbols().apply {
    currencySymbol = ""
    monetaryDecimalSeparator = ','
    groupingSeparator = '.'
}
val formatter = (DecimalFormat.getInstance() as DecimalFormat).apply {
    decimalFormatSymbols = currencySymbols
    isParseIntegerOnly = true
    roundingMode = RoundingMode.UP
}
actual fun Double.toIdrCurrency(prefix: String) = formatter.format(this).let { "${prefix} Rp $it" }.trim()
