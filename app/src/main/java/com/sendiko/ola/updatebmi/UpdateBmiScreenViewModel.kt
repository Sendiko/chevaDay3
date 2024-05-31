package com.sendiko.ola.updatebmi

import android.app.Application
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sendiko.ola.database.AppDatabase
import com.sendiko.ola.database.BmiData
import com.sendiko.ola.inputbmi.Gender
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate

class UpdateBmiScreenViewModel(application: Application): ViewModel() {

    private val dao = AppDatabase.getInstance(application).dao

    private val _state = MutableStateFlow(UpdateBmiScreenState())
    val state = _state.asStateFlow()

    fun onLoadBmiData(bmiData: BmiData) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    id = bmiData.id,
                    tinggiBadan = bmiData.tinggiBadan.toString(),
                    beratBadan = bmiData.beratBadan.toString(),
                    gender = Gender.valueOf(bmiData.gender)
                )
            }
        }
    }

    fun onTinggiBadanChanged(value: String) {
        _state.update { it.copy(tinggiBadan = value) }
    }

    fun onBeratBadanChanged(value: String) {
        _state.update { it.copy(beratBadan = value) }
    }

    fun chooseGender(gender: Gender) {
        _state.update { it.copy(gender = gender) }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun calclulateAndUpdate(bmiData: BmiData) {
        if (state.value.beratBadan.toIntOrNull() != null && state.value.tinggiBadan.toIntOrNull() != null){
            val result = state.value.beratBadan.toDouble() / ((state.value.tinggiBadan.toDouble() / 100) * (state.value.tinggiBadan.toDouble() / 100))
            val data = BmiData(
                id = bmiData.id,
                tanggal = bmiData.tanggal,
                beratBadan = bmiData.beratBadan,
                tinggiBadan = bmiData.tinggiBadan,
                nilaiBmi = result,
                gender = bmiData.gender
            )
            viewModelScope.launch {
                Log.i("BMI_LOG", "calculateBMI: $data vms")
                dao.update(data)
            }
        }
    }

}
