package com.sendiko.ola.dashboard

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sendiko.ola.navigation.Destination

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DashboardScreen(
    onNavigate: (String) -> Unit,
) {
    val bmiList = listOf(
        BmiData(
            tanggal = "12/12/12",
            beratBadan = 123,
            tinggiBadan = 123,
            nilaiBmi = 123,
            gender = "Laki - laki"
        ),
        BmiData(
            tanggal = "12/12/12",
            beratBadan = 123,
            tinggiBadan = 123,
            nilaiBmi = 123,
            gender = "Perempuan"
        ),
        BmiData(
            tanggal = "12/12/12",
            beratBadan = 123,
            tinggiBadan = 123,
            nilaiBmi = 123,
            gender = "Perempuan"
        ),
        BmiData(
            tanggal = "12/12/12",
            beratBadan = 123,
            tinggiBadan = 123,
            nilaiBmi = 123,
            gender = "Laki - laki"
        ),
        BmiData(
            tanggal = "12/12/12",
            beratBadan = 123,
            tinggiBadan = 123,
            nilaiBmi = 123,
            gender = "Laki - laki"
        ),
    )

    Scaffold(
        topBar = {
            LargeTopAppBar(title = { Text(text = "Halo, Sendiko") })
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text(text = "Tambah data BMI") },
                icon = { Icon(imageVector = Icons.Default.Add, contentDescription = "tambah") },
                onClick = { onNavigate(Destination.InputBmiScreen.name) }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues,
            content = {
                items(bmiList) { bmiData ->
                    BmiCard(
                        bmiData = bmiData,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }
        )
    }
}

@Preview
@Composable
fun DashboardScreenPrev() {
    DashboardScreen(onNavigate = {})
}

