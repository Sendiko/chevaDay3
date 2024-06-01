package com.sendiko.ola

import android.app.Application
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sendiko.ola.dashboard.DashboardScreen
import com.sendiko.ola.dashboard.DashboardScreenViewModel
import com.sendiko.ola.database.BmiData
import com.sendiko.ola.inputbmi.InputBmiScreen
import com.sendiko.ola.inputbmi.InputBmiScreenViewModel
import com.sendiko.ola.login.LoginScreen
import com.sendiko.ola.login.LoginScreenViewModel
import com.sendiko.ola.navigation.Destination
import com.sendiko.ola.register.RegisterScreen
import com.sendiko.ola.repository.ViewModelFactory
import com.sendiko.ola.ui.theme.OlaTheme
import com.sendiko.ola.updatebmi.UpdateBmiScreen
import com.sendiko.ola.updatebmi.UpdateBmiScreenViewModel

class MainActivity : ComponentActivity() {

    private fun <T : ViewModel> obtainViewModel(app: Application, modelClass: Class<T>): T {
        val factory = ViewModelFactory.getInstance(app)
        return ViewModelProvider(this, factory)[modelClass]
    }

    private val inputBmiViewModel by lazy {
        obtainViewModel(requireNotNull(application), InputBmiScreenViewModel::class.java)
    }

    private val dashboardViewModel by lazy {
        obtainViewModel(requireNotNull(application), DashboardScreenViewModel::class.java)
    }

    private val updateBmiViewModel by lazy {
        obtainViewModel(requireNotNull(application), UpdateBmiScreenViewModel::class.java)
    }

    private val loginViewModel by lazy {
        obtainViewModel(requireNotNull(application), LoginScreenViewModel::class.java)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Room database

        // AppDatabase.kt
        // Entity = Tabel = bmiTable
        // Dao, Data Access Object = getdata, savedata, update data

        setContent {
            OlaTheme {
                val navController = rememberNavController()
                var bmiShared: BmiData? = null

                NavHost(
                    navController = navController,
                    startDestination = Destination.LoginScreen.name,
                    builder = {
                        composable(
                            route = Destination.RegisterScreen.name,
                            content = {
                                RegisterScreen(
                                    onNavigate = { destination ->
                                        navController.navigate(destination)
                                    }
                                )
                            }
                        )
                        composable(
                            route = Destination.LoginScreen.name,
                            content = {
                                LoginScreen(navController, loginViewModel)
                            }
                        )
                        composable(
                            route = Destination.DashboardScreen.name,
                            content = {
                                DashboardScreen(
                                    onNavigate = { destination ->
                                        navController.navigate(destination)
                                    },
                                    viewModel = dashboardViewModel,
                                    onUpdateNavigate = {
                                        bmiShared = it
                                        navController.navigate(Destination.UpdateBmiScreen.name)
                                    }
                                )
                            }
                        )
                        composable(
                            route = Destination.InputBmiScreen.name,
                            content = {
                                InputBmiScreen(
                                    onNavigateBack = {
                                        navController.popBackStack()
                                    },
                                    viewModel = inputBmiViewModel
                                )
                            }
                        )
                        composable(
                            route = Destination.UpdateBmiScreen.name,
                            content = {
                                if (bmiShared != null) {
                                    updateBmiViewModel.onLoadBmiData(bmiShared!!)
                                }
                                UpdateBmiScreen(
                                    onNavigateBack = { navController.popBackStack() },
                                    viewModel = updateBmiViewModel
                                )
                            }
                        )
                    }
                )
            }
        }
    }
}