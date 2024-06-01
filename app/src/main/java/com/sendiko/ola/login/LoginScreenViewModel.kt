package com.sendiko.ola.login

import android.app.Application
import androidx.lifecycle.ViewModel
import com.sendiko.ola.network.NetworkConfig
import com.sendiko.ola.network.request.LoginRequest
import com.sendiko.ola.network.response.LoginResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginScreenViewModel(private val application: Application): ViewModel() {

    private val _state = MutableStateFlow(LoginScreenState())
    val state = _state.asStateFlow()

    private val client = NetworkConfig.getInstance(application)

    fun onEmailChanged(email: String) {
        _state.update { it.copy(email = email) }
    }

    fun onPasswordChanged(password: String) {
        _state.update { it.copy(password = password) }
    }

    fun onLogin() {
        _state.update { it.copy(isLoading = true) }
        val data = LoginRequest(
            email = state.value.email,
            password = state.value.password
        )
        val request = client.postLogin(data)
        request.enqueue(
            object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    _state.update { it.copy(isLoading = false) }
                    when (response.code()) {
                        200 -> {
                            _state.update {
                                it.copy(
                                    isLoginSuccessful = true,
                                    notificationMessage = "Berhasil login."
                                )
                            }
                        }
                        404 -> {
                            _state.update {
                                it.copy(
                                    isLoginSuccessful = false,
                                    notificationMessage = "Gagal login."
                                )
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    _state.update { it.copy(isLoading = false) }
                }

            }
        )
    }

}