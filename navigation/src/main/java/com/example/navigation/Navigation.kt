package com.example.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.navigation.internal.InternalNavigationState
import com.example.navigation.internal.ScreenMultiStack
import com.example.navigation.internal.ScreenStack
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
): Navigation {
    val screenStack = rememberSaveable(rootRoutes) {
        val stacks = SnapshotStateList<ScreenStack>()
        stacks.addAll(rootRoutes.map(::ScreenStack))
        ScreenMultiStack(stacks, initialIndex)
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