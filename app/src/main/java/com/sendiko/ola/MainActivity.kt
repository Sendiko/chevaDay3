package com.sendiko.ola

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sendiko.ola.dashboard.DashboardScreen
import com.sendiko.ola.login.LoginScreen
import com.sendiko.ola.navigation.Destination
import com.sendiko.ola.register.RegisterScreen
import com.sendiko.ola.ui.theme.OlaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
                                    }
                                )
                            }
                        )
                    }
                )
            }
        }
    }
}