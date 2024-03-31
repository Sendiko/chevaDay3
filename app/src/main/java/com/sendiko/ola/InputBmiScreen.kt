package com.sendiko.ola

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sendiko.ola.ui.components.NumberTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputBmiScreen(
    onNavigateBack: () -> Unit,
) {
    var tinggiBadan by remember {
        mutableStateOf("")
    }
    var beratBadan by remember {
        mutableStateOf("")
    }

    fun calculateBMI(): Int {
        return if (beratBadan.toIntOrNull() != null && tinggiBadan.toIntOrNull() != null){
            beratBadan.toInt() / (tinggiBadan.toInt() * tinggiBadan.toInt())
        } else 0
    }

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
                value = tinggiBadan,
                onNewValue = { newValue ->
                    tinggiBadan = newValue
                },
                modifier = Modifier.padding(8.dp).fillMaxWidth()
            )
            Text(
                text = "Masukkan berat badan",
                modifier = Modifier.padding(8.dp),
                fontSize = 16.sp
            )
            NumberTextField(
                value = beratBadan,
                onNewValue = { newValue ->
                    beratBadan = newValue
                },
                modifier = Modifier.padding(8.dp).fillMaxWidth()
            )
            Text(text = "Hasil BMI: ${calculateBMI()}")
            Button(onClick = { calculateBMI() }, modifier = Modifier.padding(8.dp).fillMaxWidth()) {
                Text(text = "Hitung dan Simpan BMI")
            }
        }
    }
}

@Preview
@Composable
fun InputBmiPrev() {
    InputBmiScreen(onNavigateBack = {})
}