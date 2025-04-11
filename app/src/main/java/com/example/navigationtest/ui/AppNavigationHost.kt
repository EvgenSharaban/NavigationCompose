package com.example.navigationtest.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.navigation.Navigation
import com.example.navigation.NavigationHost
import com.example.navigationtest.ui.screens.AddItemScreen
import com.example.navigationtest.ui.screens.ItemsScreen
import com.example.navigationtest.ui.screens.ProfileScreen
import com.example.navigationtest.ui.screens.SettingsScreen

@Composable
fun AppNavigationHost(
    paddingValues: PaddingValues,
    navigation: Navigation
) {
    NavigationHost(
        navigation = navigation,
        modifier = Modifier.padding(paddingValues)
    ) { currentRoute ->
        when (currentRoute) {
            AppRoute.Tab.Items -> ItemsScreen()
            AppRoute.Tab.Settings -> SettingsScreen()
            AppRoute.Tab.Profile -> ProfileScreen()
            AppRoute.AddItem -> AddItemScreen()
        }
    }
}