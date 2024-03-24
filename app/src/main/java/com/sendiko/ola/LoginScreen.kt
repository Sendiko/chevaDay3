package com.sendiko.ola

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreen() {
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
        Button(onClick = { /*TODO*/ }, modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)) {
            Text(text = "Login")
        }
    }

}

@Preview
@Composable
fun LoginScreenPrev() {
    Surface {
        LoginScreen()
    }
}