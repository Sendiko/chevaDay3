package com.sendiko.ola.updatebmi

import com.sendiko.ola.inputbmi.Gender

data class UpdateBmiScreenState(
    val id: Int = 0,
    val tinggiBadan: String = "",
    val beratBadan: String = "",
    val gender: Gender = Gender.None,
)
