package com.example.navigationtest.ui.scaffold

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.navigation.Navigation
import com.example.navigationtest.RootTabs

@Composable
fun AppNavigationBar(navigation: Navigation) {
    val (router, navigationState) = navigation

    if (navigationState.isRoot) {
        NavigationBar {
            RootTabs.forEach { tab ->
                NavigationBarItem(
                    selected = navigationState.currentRoute == tab,
                    label = { Text(stringResource(tab.titleRes)) },
                    onClick = {
                        router.restart(tab)
                    },
                    icon = {
                        Icon(
                            imageVector = tab.icon,
                            contentDescription = stringResource(tab.titleRes)
                        )
                    }
                )
            }
        }
    }
}