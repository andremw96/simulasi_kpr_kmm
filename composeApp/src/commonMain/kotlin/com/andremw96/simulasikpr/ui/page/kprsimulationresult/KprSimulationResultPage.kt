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
import com.andremw96.simulasikpr.ui.widget.KprSimTable
import com.andremw96.simulasikpr.ui.widget.KprSimTopAppBar
import org.jetbrains.compose.resources.stringResource
import simulasikpr.composeapp.generated.resources.Res
import simulasikpr.composeapp.generated.resources.string_tenor
import simulasikpr.composeapp.generated.resources.string_total_pinjaman

@Composable
fun KprSimulationResultPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        KprSimTopAppBar()
        LoanInputs()
        RepaymentTable()
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
fun LoanInputs() {
    Column(modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp)) {
        InputField(label = stringResource(Res.string.string_total_pinjaman), "Rp. 300.000.000")
        Spacer(modifier = Modifier.height(8.dp))
        InputField(label = stringResource(Res.string.string_tenor), "12")
        Spacer(modifier = Modifier.height(8.dp))
        Interests()
    }
}

@Composable
fun Interests() {
    InputField(label = "Bunga Fixed (1-5 tahun)", "4.5%")
}

@Composable
fun RepaymentTable() {
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
        // Just a fake data... a Pair of Int and String
        KprSimTable(
            modifier = Modifier.fillMaxSize(),
            columnCount = 3,
            rowCount = 10,
            cellContent = { columnIndex, rowIndex ->
                TableCell(
                    text = "Column: $columnIndex; Row: $rowIndex",
                )
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
            .border(1.dp, Color.Black)
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
