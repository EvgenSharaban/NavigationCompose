package com.example.navigationtest.ui

import com.example.navigation.Route
import com.example.navigationtest.ui.screens.AddItemScreenProducer
import com.example.navigationtest.ui.screens.ItemsScreenProducer
import com.example.navigationtest.ui.screens.ProfileScreenProducer
import com.example.navigationtest.ui.screens.SettingsScreenProducer
import kotlinx.parcelize.Parcelize

sealed class AppRoute(
    override val screenProducer: () -> AppScreen
) : Route {

    @Parcelize
    data object AddItem : AppRoute(AddItemScreenProducer)

    sealed class Tab(
        screenProducer: () -> AppScreen
    ) : AppRoute(screenProducer) {
        @Parcelize
        data object Items : Tab(ItemsScreenProducer)
        @Parcelize
        data object Settings : Tab(SettingsScreenProducer)
        @Parcelize
        data object Profile : Tab(ProfileScreenProducer)
    }
}

val RootTabs = listOf(AppRoute.Tab.Items, AppRoute.Tab.Settings, AppRoute.Tab.Profile)