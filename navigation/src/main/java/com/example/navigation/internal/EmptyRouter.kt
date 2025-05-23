package com.example.navigation.internal

import com.example.navigation.Route
import com.example.navigation.Router

internal object EmptyRouter : Router {
    override fun launch(route: Route) = Unit
    override fun pop(response: Any?) = Unit
    override fun restart(rootRoutes: List<Route>, initialIndex: Int) = Unit
    override fun switchStack(index: Int) = Unit
}