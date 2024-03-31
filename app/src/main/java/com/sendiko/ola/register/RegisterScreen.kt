package com.sendiko.ola.register

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sendiko.ola.navigation.Destination

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RegisterScreen(
    onNavigate: (String) -> Unit,
) {
    var usernameText by remember {
        mutableStateOf("")
    }
    var emailText by remember {
        mutableStateOf("")
    }
    var passwordText by remember {
        mutableStateOf("")
    }
    var isPasswordTextVisible by remember {
        mutableStateOf(false)
    }
    var passwordConfirmationText by remember {
        mutableStateOf("")
    }
    var isPasswordConfirmationTextVisible by remember {
        mutableStateOf(false)
    }
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Register",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(text = "Daftar akun dulu yuk!")
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = usernameText,
                onValueChange = {
                    usernameText = it
                },
                placeholder = { Text(text = "Pilih username kamu")}
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = emailText,
                onValueChange = {
                    emailText = it
                },
                placeholder = { Text(text = "Masukkan email kamu")}
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = passwordText,
                onValueChange = {
                    passwordText = it
                },
                placeholder = { Text(text = "Pilih password kamu")},
                visualTransformation = 
                    if (!isPasswordTextVisible) PasswordVisualTransformation()
                        else VisualTransformation.None,
                trailingIcon = {
                    IconButton(onClick = { isPasswordTextVisible = !isPasswordTextVisible }) {
                        Icon(imageVector = if (isPasswordTextVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility , contentDescription = "" )
                    }
                }
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = passwordConfirmationText,
                onValueChange = {
                    passwordConfirmationText = it
                },
                placeholder = { Text(text = "Ulangi password kamu")},
                visualTransformation =
                    if (!isPasswordConfirmationTextVisible) PasswordVisualTransformation()
                        else VisualTransformation.None,
                trailingIcon = {
                    IconButton(onClick = { isPasswordConfirmationTextVisible = !isPasswordConfirmationTextVisible }) {
                        Icon(imageVector = if (isPasswordConfirmationTextVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility , contentDescription = "" )
                    }
                }
            )
            Button(
                onClick = { onNavigate(Destination.LoginScreen.name) },
                content = {
                    Text(text = "Buat akun")
                }
            )
        }
    }
}