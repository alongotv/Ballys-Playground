package com.alongo.navigation.hosts.main

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.alongo.feature_welcome.WelcomeView

@Composable
fun MainNavigationHost(
    navHostController: NavHostController,
    startDestination: String
) {
    NavHost(navController = navHostController, startDestination = startDestination) {
        composable(MainNavigationDestination.Welcome) {
            WelcomeView(hiltViewModel())
        }
    }
}
