package com.alongo.navigation.hosts

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.alongo.navigation.hosts.main.MainNavigationHost

@Composable
fun NavigationHost(
    navHostController: NavHostController,
    navigationHostType: NavigationHostType
) {

    when (navigationHostType) {
        is NavigationHostType.Main -> {
            MainNavigationHost(
                navHostController = navHostController,
                startDestination = navigationHostType.startDestination
            )
        }
    }
}