package com.example.navigationtest.ui

import com.example.navigation.Route
import com.example.navigationtest.ui.screens.ProfileScreenProducer
import com.example.navigationtest.ui.screens.SettingsScreenProducer
import com.example.navigationtest.ui.screens.item.ItemScreenArgs
import com.example.navigationtest.ui.screens.item.itemScreenProducer
import com.example.navigationtest.ui.screens.items.ItemsScreenProducer
import kotlinx.collections.immutable.persistentListOf
import kotlinx.parcelize.Parcelize

sealed class AppRoute(
    override val screenProducer: () -> AppScreen
) : Route {

    @Parcelize
    data class Item(
        val args: ItemScreenArgs
    ) : AppRoute(itemScreenProducer(args))

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

val RootTabs =
    persistentListOf(AppRoute.Tab.Items, AppRoute.Tab.Settings, AppRoute.Tab.Profile)