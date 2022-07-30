package com.alongo.feature_welcome

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun WelcomeView(welcomeViewModel: WelcomeViewModel) {
    Column {
        Button(onClick = { welcomeViewModel.handleEvent(WelcomeViewModel.Event.OnOpenMockDataListClicked) }) {
            Text(text = "Open Mock Data List")
        }
    }
}