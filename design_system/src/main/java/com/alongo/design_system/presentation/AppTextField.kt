package com.alongo.design_system.presentation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun AppTextField(value: String, onValueChange: (String) -> Unit, modifier: Modifier = Modifier, placeholder: @Composable () -> Unit = {}) {
    TextField(value = value, onValueChange = onValueChange, modifier = modifier.fillMaxWidth(), placeholder = {
        placeholder()
    })
}

@Composable
@Preview
fun AppTextField_Preview() {
    AppTextField("Example", onValueChange = {})
}