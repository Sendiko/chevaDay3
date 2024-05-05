package com.sendiko.ola.inputbmi

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sendiko.ola.ui.components.NumberTextField

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputBmiScreen(
    onNavigateBack: () -> Unit,
    viewModel: InputBmiScreenViewModel
) {
    val screenState = viewModel.state.collectAsState().value
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Input BMI Data") },
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
                    modifier = Modifier.padding(8.dp).weight(1f)
                )
                InputChip(
                    selected = screenState.gender == Gender.Female,
                    onClick = { viewModel.chooseGender(Gender.Female) },
                    label = { Text(text = "Perempuan") },
                    modifier = Modifier.padding(8.dp).weight(1f)
                )
            }
            Button(onClick = { viewModel.calculateBMI() }, modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()) {
                Text(text = "Hitung dan Simpan BMI")
            }
        }
    }
}