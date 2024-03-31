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
    navController: NavController
) {
    // Login
    // Silahkan masuk ke akun anda
    // textfield username
    // textfield password
    // button login
    
    var teksUsername by remember {
        mutableStateOf("")
    }
    var teksPassword by remember {
        mutableStateOf("")
    }
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
            value = teksUsername,
            onValueChange = { teksBaru ->
                teksUsername = teksBaru
            },
            placeholder = {
                Text(text = "Masukkan username")
            }
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            value = teksPassword,
            onValueChange = { teksBaru ->
                teksPassword = teksBaru
            },
            placeholder = {
                Text(text = "Masukkan password")
            }
        )
        Button(onClick = { navController.navigate(Destination.DashboardScreen.name) }, modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)) {
            Text(text = "Login")
        }
        TextButton(onClick = { navController.navigate(Destination.RegisterScreen.name) }) {
            Text(text = "Daftar")
        }
    }

}

@Preview
@Composable
fun LoginScreenPrev() {
    Surface {
        LoginScreen(rememberNavController())
    }
}