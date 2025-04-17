package com.example.navigation.links

import com.example.navigation.Route

data class StackState(
    val routes: List<Route>,
) {

    fun withNewRoute(route: Route): StackState = copy(
        routes = routes + route
    )

}