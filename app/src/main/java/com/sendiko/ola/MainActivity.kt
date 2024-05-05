package com.sendiko.ola

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sendiko.ola.dashboard.DashboardScreen
import com.sendiko.ola.inputbmi.InputBmiScreen
import com.sendiko.ola.inputbmi.InputBmiScreenViewModel
import com.sendiko.ola.login.LoginScreen
import com.sendiko.ola.navigation.Destination
import com.sendiko.ola.register.RegisterScreen
import com.sendiko.ola.repository.ViewModelFactory
import com.sendiko.ola.ui.theme.OlaTheme

class MainActivity : ComponentActivity() {

    private fun <T : ViewModel> obtainViewModel(app: Application, modelClass: Class<T>): T {
        val factory = ViewModelFactory.getInstance(app)
        return ViewModelProvider(this, factory)[modelClass]
    }

    private val inputBmiViewModel by lazy {
        obtainViewModel(requireNotNull(application), InputBmiScreenViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Room database

        // Appdatabase.kt
        // Entity = Tabel = bmiTbale
        // Dao, Data Access Object = getdata, savedata, update data

        setContent {
            OlaTheme {
                val navController = rememberNavController()

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
                                LoginScreen(navController)
                            }
                        )
                        composable(
                            route = Destination.DashboardScreen.name,
                            content = {
                                DashboardScreen(
                                    onNavigate = { destination ->
                                        navController.navigate(destination)
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
                    }
                )
            }
        }
    }
}