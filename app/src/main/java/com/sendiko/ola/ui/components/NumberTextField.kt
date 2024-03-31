package com.sendiko.ola.ui.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun NumberTextField(
    modifier: Modifier = Modifier,
    value: String,
    onNewValue: (String) -> Unit,
) {
    OutlinedTextField(
        value = value,
        onValueChange = { onNewValue(it) },
        modifier = modifier,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
        shape = CircleShape
    )
}