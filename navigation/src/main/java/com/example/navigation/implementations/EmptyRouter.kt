package com.example.navigation.implementations

import com.example.navigation.Route
import com.example.navigation.Router

internal object EmptyRouter : Router {
    override fun launch(route: Route) = Unit
    override fun pop() = Unit
    override fun restart(route: Route) = Unit
}