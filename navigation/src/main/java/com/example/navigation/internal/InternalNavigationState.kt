package com.example.navigation.internal

import com.example.navigation.Route
import kotlinx.coroutines.flow.Flow

internal sealed class NavigationEvent {
    data class Removed(val route: Route) : NavigationEvent()
}

internal interface InternalNavigationState {
    fun listen(): Flow<NavigationEvent>
}