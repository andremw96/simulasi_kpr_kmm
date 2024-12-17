package com.andremw96.simulasikpr.ui.page.kprsimulation

actual class DecimalFormat actual constructor() {
    actual fun format(double: Double): String {
        val df = java.text.DecimalFormat()
        df.isGroupingUsed = false
        df.maximumFractionDigits = 2
        df.isDecimalSeparatorAlwaysShown = false
        return df.format(double)
    }
}