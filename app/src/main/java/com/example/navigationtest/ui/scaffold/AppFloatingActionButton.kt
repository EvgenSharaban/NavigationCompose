package com.example.navigationtest.ui.scaffold

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.navigation.Navigation
import com.example.navigationtest.R
import com.example.navigationtest.ui.AppRoute

@Composable
fun AppFloatingActionButton(navigation: Navigation) {
    val (router, navigationState) = navigation

    if (navigationState.currentRoute == AppRoute.Tab.Items) {
        FloatingActionButton(
            onClick = { router.launch(AppRoute.AddItem) }
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = stringResource(R.string.add_new_item)
            )
        }
    }
}