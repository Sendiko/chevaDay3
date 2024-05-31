package com.sendiko.ola.repository

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sendiko.ola.dashboard.DashboardScreenViewModel
import com.sendiko.ola.inputbmi.InputBmiScreenViewModel
import com.sendiko.ola.updatebmi.UpdateBmiScreenViewModel

class ViewModelFactory private constructor(
    private val application: Application
): ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        var INSTANCE: ViewModelFactory?= null

        @JvmStatic
        fun getInstance(app: Application): ViewModelFactory {
            when(INSTANCE){
                null -> synchronized(ViewModelFactory::class.java){
                    INSTANCE = ViewModelFactory(app)
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(InputBmiScreenViewModel::class.java) -> InputBmiScreenViewModel(application) as T
            modelClass.isAssignableFrom(DashboardScreenViewModel::class.java) -> DashboardScreenViewModel(application) as T
            modelClass.isAssignableFrom(UpdateBmiScreenViewModel::class.java) -> UpdateBmiScreenViewModel(application) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}