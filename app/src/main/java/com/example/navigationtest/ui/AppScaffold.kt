package com.example.navigationtest.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.navigation.rememberNavigation
import com.example.navigationtest.ui.scaffold.AppFloatingActionButton
import com.example.navigationtest.ui.scaffold.AppNavigationBar
import com.example.navigationtest.ui.scaffold.AppToolbar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold() {
    val navigation = rememberNavigation(initialRoute = AppRoute.Tab.Items)
    val (router, navigationState) = navigation
    val environment = navigationState.currentScreen.environment as AppScreenEnvironment
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AppToolbar(
                titleRes = environment.titleRes,
                isRoot = navigationState.isRoot,
                dropdownItemsList = environment.dropdownList,
                onPopAction = router::pop,
            )
        },
        floatingActionButton = {
            AppFloatingActionButton(
                floatingAction = environment.floatingAction
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        bottomBar = {
            if (navigationState.isRoot) {
                AppNavigationBar(
                    currentRoute = navigationState.currentRoute,
                    onRouteSelected = router::restart
                )
            }
        }
    ) { paddingValues ->
        AppNavigationHost(
            modifier = Modifier.padding(paddingValues),
            navigation = navigation
        )
    }
}