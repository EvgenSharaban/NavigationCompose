package com.example.navigationtest.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.example.navigation.rememberNavigation
import com.example.navigationtest.ui.scaffold.AppFloatingActionButton
import com.example.navigationtest.ui.scaffold.AppNavigationBar
import com.example.navigationtest.ui.scaffold.AppToolbar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold() {
    val navigation = rememberNavigation(initialRoute = AppRoute.Tab.Items)

    Scaffold(
        topBar = {
            AppToolbar(navigation)
        },
        floatingActionButton = { AppFloatingActionButton(navigation) },
        floatingActionButtonPosition = FabPosition.End,
        bottomBar = { AppNavigationBar(navigation) }
    ) { paddingValues ->
        AppNavigationHost(paddingValues, navigation)
    }
}