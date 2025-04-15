package com.example.navigationtest.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.navigation.Navigation
import com.example.navigation.NavigationHost
import com.example.navigation.rememberNavigation

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

@Preview(showSystemUi = true)
@Composable
private fun AppNavigationHostPreview() {
    AppNavigationHost(
        navigation = rememberNavigation(RootTabs)
    )
}