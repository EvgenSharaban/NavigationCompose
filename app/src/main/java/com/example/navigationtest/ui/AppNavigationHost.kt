package com.example.navigationtest.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.navigation.Navigation
import com.example.navigation.NavigationHost

@Composable
fun AppNavigationHost(
    navigation: Navigation,
    modifier: Modifier = Modifier
) {
    NavigationHost(
        navigation = navigation,
        modifier = modifier
    )
}