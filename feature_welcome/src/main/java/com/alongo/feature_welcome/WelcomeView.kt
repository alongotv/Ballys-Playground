package com.alongo.feature_welcome

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun WelcomeView(welcomeViewModel: WelcomeViewModel) {
    Column {
        Text(welcomeViewModel.welcomeText)
    }
}