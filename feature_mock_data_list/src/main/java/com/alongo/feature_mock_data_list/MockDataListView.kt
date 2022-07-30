package com.alongo.feature_mock_data_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.alongo.design_system.presentation.AppTextField

@Composable
fun MockDataListView(viewModel: MockDataListViewModel) {
    when (val state = viewModel.state.value) {
        is MockDataListViewModel.State.DataReady -> {
            LazyColumn(modifier = Modifier.padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                items(state.data) { item ->
                    AppTextField(value = item.text, onValueChange = { newValue ->
                        viewModel.handleEvent(
                            MockDataListViewModel.Event.OnDataUpdated(
                                item,
                                newValue
                            )
                        )
                    })
                    Divider(color = Color.Black)
                }

                item {
                    Button(onClick = { viewModel.handleEvent(MockDataListViewModel.Event.OnDataCreated) }) {
                        Text(text = "Create Data")
                    }
                }
            }
        }

        is MockDataListViewModel.State.Idle, MockDataListViewModel.State.LoadingData -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.handleEvent(MockDataListViewModel.Event.OnScreenAppeared)
        viewModel.handleEvent(MockDataListViewModel.Event.RequestData)
    }
}
