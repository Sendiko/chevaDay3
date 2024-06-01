package com.sendiko.ola.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sendiko.ola.R
import com.sendiko.ola.navigation.Destination

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginScreenViewModel
) {
    val screenState by viewModel.state.collectAsState()

    // TODO: admin@admin.com
    // TODO: admin12345

    LaunchedEffect(key1 = screenState.isLoginSuccessful) {
        if (screenState.isLoginSuccessful) {
            navController.navigate(Destination.DashboardScreen.name)
        }
    }

    Surface {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
        ) {

            Image(
                painter = painterResource(id = R.drawable.login_illustration),
                contentDescription = "ilustrasi login"
            )

            Text(
                text = "Login",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            Text(
                text = "Silahkan masuk ke akun anda",
                modifier = Modifier.padding(vertical = 8.dp)
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                value = screenState.email,
                onValueChange = { teksBaru ->
                    viewModel.onEmailChanged(teksBaru)
                },
                placeholder = {
                    Text(text = "Masukkan email")
                }
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                value = screenState.password,
                onValueChange = { teksBaru ->
                    viewModel.onPasswordChanged(teksBaru)
                },
                placeholder = {
                    Text(text = "Masukkan password")
                }
            )
            Text(text = screenState.notificationMessage)
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                onClick = {
                    viewModel.onLogin()
                },
                content = {
                    Text(text = "Login")
                }
            )
            TextButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                onClick = { navController.navigate(Destination.RegisterScreen.name) },
                content = {
                    Text(text = "Daftar")
                }
            )
        }
    }

}