package com.andremw96.simulasikpr.ui.page.kprsimulationresult

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.andremw96.simulasikpr.ui.page.kprsimulation.KprSimulationPageState
import com.andremw96.simulasikpr.ui.page.kprsimulation.model.SimulationResult
import com.andremw96.simulasikpr.ui.page.kprsimulation.toIdrCurrency
import com.andremw96.simulasikpr.ui.widget.KprSimTable
import com.andremw96.simulasikpr.ui.widget.KprSimTopAppBar
import org.jetbrains.compose.resources.stringResource
import simulasikpr.composeapp.generated.resources.Res
import simulasikpr.composeapp.generated.resources.string_tenor
import simulasikpr.composeapp.generated.resources.string_total_pinjaman

@Composable
fun KprSimulationResultPage(
    viewState: KprSimulationPageState,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        KprSimTopAppBar()
        LoanInputs(
            totalLoan = (viewState.housePrice.toDouble() - viewState.downPaymentCurrency.toDouble()).toIdrCurrency(),
            totalTenor = viewState.tenor,
        )
        RepaymentTable(viewState.simulationResult)
    }
}

@Composable
fun InputField(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label)
        Text(value)
    }
}

@Composable
fun LoanInputs(
    totalLoan: String,
    totalTenor: String,
) {
    Column(modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp)) {
        InputField(label = stringResource(Res.string.string_total_pinjaman), totalLoan)
        Spacer(modifier = Modifier.height(8.dp))
        InputField(label = stringResource(Res.string.string_tenor), totalTenor)
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
fun RepaymentTable(simulationResult: List<SimulationResult>) {
    Column(modifier = Modifier.padding(16.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Tabel Angsuran")
            GroupByDropDown()
        }
        Spacer(modifier = Modifier.height(8.dp))
        KprSimTable(
            modifier = Modifier.fillMaxSize(),
            columnCount = 6,
            rowCount = simulationResult.size,
            cellContent = { columnIndex, rowIndex ->
                val data = simulationResult[rowIndex]
                when (columnIndex) {
                    0 -> TableCell(text = data.currentMonth)
                    1 -> TableCell(text = data.interestRate)
                    2 -> TableCell(text = if (rowIndex == 0) data.interestPayment else data.interestPayment.toDouble().toIdrCurrency())
                    3 -> TableCell(text = if (rowIndex == 0) data.principalPayment else data.principalPayment.toDouble().toIdrCurrency())
                    4 -> TableCell(text = if (rowIndex == 0) data.monthlyInstallment else data.monthlyInstallment.toDouble().toIdrCurrency())
                    5 -> TableCell(text = if (rowIndex == 0) data.remainingLoanEnd else data.remainingLoanEnd.toDouble().toIdrCurrency())
                }
            })
    }
}

@Composable
fun TableCell(
    text: String,
) {
    Text(
        text = text,
        Modifier
            .padding(8.dp)
    )
}

@Composable
fun GroupByDropDown() {
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("Group by") }
    Box(modifier = Modifier.wrapContentSize(Alignment.TopStart)) {
        Button(onClick = { expanded = true }) {
            Text(text = selectedOption)
            Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "Dropdown")
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            DropdownMenuItem(text = {
                Text(text = "Option 1")
            }, onClick = {
                selectedOption = "Option 1"
                expanded = false
            })
            DropdownMenuItem(text = {
                Text(text = "Option 2")
            }, onClick = {
                selectedOption = "Option 2"
                expanded = false
            })
        }
    }
}
