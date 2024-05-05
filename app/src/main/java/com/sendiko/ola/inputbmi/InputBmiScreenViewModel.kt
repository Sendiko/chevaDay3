package com.sendiko.ola.inputbmi

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sendiko.ola.database.AppDatabase
import com.sendiko.ola.database.BmiData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate

class InputBmiScreenViewModel (
    application: Application
): ViewModel() {

    val dao = AppDatabase.getInstance(application).dao

    private val _state = MutableStateFlow(InputBmiScreenState())
    val state = _state.asStateFlow()

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
    fun calculateBMI() {
        if (state.value.beratBadan.toIntOrNull() != null && state.value.tinggiBadan.toIntOrNull() != null){
            val result = state.value.beratBadan.toInt() / (state.value.tinggiBadan.toInt() * state.value.tinggiBadan.toInt())
            val data = BmiData(
                tanggal = LocalDate.now().toString(),
                beratBadan = state.value.beratBadan.toInt(),
                tinggiBadan = state.value.tinggiBadan.toInt(),
                nilaiBmi = result,
                gender = state.value.gender.name
            )
            viewModelScope.launch {
                dao.insert(data)
            }
        }
    }

}