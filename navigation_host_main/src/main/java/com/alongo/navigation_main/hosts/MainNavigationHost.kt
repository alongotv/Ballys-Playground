package com.alongo.navigation_main.hosts

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.alongo.feature_mock_data_list.MockDataListView
import com.alongo.feature_timezones_list.TimezonesListView
import com.alongo.feature_welcome.WelcomeView
import com.alongo.nav_destinations.main.MainNavigationDestination

@Composable
fun MainNavigationHost(
    navHostController: NavHostController,
    startDestination: String
) {
    NavHost(navController = navHostController, startDestination = startDestination) {
        composable(MainNavigationDestination.Welcome) {
            WelcomeView(hiltViewModel())
        }
        composable(MainNavigationDestination.MockDataList) {
            MockDataListView(hiltViewModel())
        }
        composable(MainNavigationDestination.TimezonesList) {
            TimezonesListView(hiltViewModel())
        }
    }
}
