package com.alongo.feature_welcome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

@Composable
fun WelcomeView(welcomeViewModel: WelcomeViewModel) {
    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Button(onClick = { welcomeViewModel.handleEvent(WelcomeViewModel.Event.OnOpenMockDataListClicked) }) {
            Text(text = stringResource(R.string.welcome_open_data_list_title))
        }

        Button(onClick = { welcomeViewModel.handleEvent(WelcomeViewModel.Event.OnOpenTimezonesListClicked) }) {
            Text(text = stringResource(R.string.welcome_open_timezones_list_title))
        }
    }
}