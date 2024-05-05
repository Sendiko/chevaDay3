package com.sendiko.ola.inputbmi

data class InputBmiScreenState(
    val tinggiBadan: String = "",
    val beratBadan: String = "",
    val gender: Gender = Gender.None,
)
