package com.sendiko.ola.dashboard

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sendiko.ola.database.AppDatabase
import com.sendiko.ola.database.BmiData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DashboardScreenViewModel(application: Application): ViewModel() {

    private val dao = AppDatabase.getInstance(application).dao

    private val _state = MutableStateFlow(DashboardScreenState())
    val state = _state.asStateFlow()

    fun loadBmiData() {
        viewModelScope.launch {
            dao.getAll().collect { bmiList ->
                _state.update { it.copy(bmiList = bmiList) }
            }
        }
    }

    fun onDeleteBmi(bmiData: BmiData) {
        viewModelScope.launch {
            dao.delete(bmiData)
        }
    }

}