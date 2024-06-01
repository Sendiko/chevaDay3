package com.sendiko.ola.login

data class LoginScreenState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val notificationMessage: String = "",
    val isLoginSuccessful: Boolean = false,
)
