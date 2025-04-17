package com.example.navigation

import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.toMutableStateList
import com.example.navigation.internal.InternalNavigationState
import com.example.navigation.internal.ScreenMultiStack
import com.example.navigation.internal.ScreenStack
import com.example.navigation.links.DeepLinkHandler
import com.example.navigation.links.MultiStackState
import com.example.navigation.links.StackState
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@ConsistentCopyVisibility
@Stable
data class Navigation internal constructor(
    val router: Router,
    val navigationState: NavigationState,
    internal val internalNavigationState: InternalNavigationState
)

@Composable
fun rememberNavigation(
    rootRoutes: ImmutableList<Route>,
    initialIndex: Int = 0,
    deepLinkHandler: DeepLinkHandler = DeepLinkHandler.DEFAULT
): Navigation {
    val activity = LocalActivity.current
    val screenStack = rememberSaveable(rootRoutes) {
        val inputState = MultiStackState(
            activeStackIndex = initialIndex,
            stacks = rootRoutes.map { rootRoute -> StackState(listOf(rootRoute)) }
        )
        val outputState = activity?.intent?.data?.let { deepLinkUri ->
            deepLinkHandler.handleDeepLink(deepLinkUri, inputState)
        } ?: inputState

        ScreenMultiStack(
            initialIndex = outputState.activeStackIndex,
            stacks = outputState.stacks.map { stackState ->
                ScreenStack(stackState.routes)
            }.toMutableStateList()
        )
    }

    return remember(rootRoutes) {
        Navigation(
            router = screenStack,
            navigationState = screenStack,
            internalNavigationState = screenStack
        )
    }
}

@Composable
fun rememberNavigation(
    initialRoute: Route
): Navigation = rememberNavigation(persistentListOf(initialRoute))