package com.alongo.navigation.hosts

sealed class NavigationHostType(val startDestination: String) {
    class Main(startDestination: String) : NavigationHostType(startDestination)
}
