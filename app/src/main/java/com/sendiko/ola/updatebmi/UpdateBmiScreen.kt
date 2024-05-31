package com.sendiko.ola.updatebmi

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.InputChip
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sendiko.ola.database.BmiData
import com.sendiko.ola.inputbmi.Gender
import com.sendiko.ola.ui.components.NumberTextField
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateBmiScreen(
    modifier: Modifier = Modifier,
    onNavigateBack: () -> Unit,
    viewModel: UpdateBmiScreenViewModel
) {
    val screenState = viewModel.state.collectAsState().value
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Update BMI Data") },
                navigationIcon = {
                    IconButton(onClick = { onNavigateBack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "kembali"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Text(
                text = "Masukkan tinggi badan",
                modifier = Modifier.padding(8.dp),
                fontSize = 16.sp
            )
            NumberTextField(
                value = screenState.tinggiBadan,
                onNewValue = { newValue ->
                    viewModel.onTinggiBadanChanged(newValue)
                },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            )
            Text(
                text = "Masukkan berat badan",
                modifier = Modifier.padding(8.dp),
                fontSize = 16.sp
            )
            NumberTextField(
                value = screenState.beratBadan,
                onNewValue = { newValue ->
                    viewModel.onBeratBadanChanged(newValue)
                },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            )
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                InputChip(
                    selected = screenState.gender == Gender.Male,
                    onClick = { viewModel.chooseGender(Gender.Male) },
                    label = { Text(text = "Laki - laki") },
                    modifier = Modifier
                        .padding(8.dp)
                        .weight(1f)
                )
                InputChip(
                    selected = screenState.gender == Gender.Female,
                    onClick = { viewModel.chooseGender(Gender.Female) },
                    label = { Text(text = "Perempuan") },
                    modifier = Modifier
                        .padding(8.dp)
                        .weight(1f)
                )
            }
            Button(
                onClick = {
                    val data = BmiData(
                        id = screenState.id,
                        tanggal = LocalDate.now().toString(),
                        beratBadan = screenState.beratBadan.toInt(),
                        tinggiBadan = screenState.tinggiBadan.toInt(),
                        gender = screenState.gender.name,
                        nilaiBmi = 0.0
                    )
                    viewModel.calclulateAndUpdate(data)
                },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "Hitung dan Update BMI")
            }
        }
    }
}