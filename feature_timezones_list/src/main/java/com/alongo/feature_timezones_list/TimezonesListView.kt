package com.alongo.feature_timezones_list

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TimezonesListView(timezonesListViewModel: TimezonesListViewModel) {
    LazyColumn {
        timezonesListViewModel.displayTimezones.forEach { item ->
            item {
                TimezoneView(name = item.name, subtitle = item.currentTime)
            }
        }
    }

    LaunchedEffect(key1 = Unit) {
        timezonesListViewModel.getTimezones()
    }
}

@Composable
fun TimezoneView(name: String, subtitle: String) {
    Row(modifier = Modifier) {
        Text(name, modifier = Modifier.weight(1f))
        Text(subtitle, modifier = Modifier.weight(1f))
    }
}

@Composable
@Preview
fun TimezoneViewPreview() {
    TimezoneView("Name", "Subtitle")
}