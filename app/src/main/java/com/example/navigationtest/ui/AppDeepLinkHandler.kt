package com.example.navigationtest.ui

import android.net.Uri
import android.util.Log
import com.example.navigation.links.DeepLinkHandler
import com.example.navigation.links.MultiStackState
import com.example.navigationtest.ui.screens.item.ItemScreenArgs

object AppDeepLinkHandler : DeepLinkHandler {

    override fun handleDeepLink(
        uri: Uri,
        inputState: MultiStackState
    ): MultiStackState {
        var outputState = inputState
        if (uri.scheme == "nav") {
            when (uri.host) {
                "settings" -> {
                    outputState = inputState.copy(activeStackIndex = 1)
                }

                "profile" -> {
                    outputState = inputState.copy(activeStackIndex = 2)
                }

                "items" -> {
                    val itemIndex = uri.pathSegments?.firstOrNull()?.toIntOrNull()
                    Log.d("AAAA", "handleDeepLink: item index = $itemIndex")
                    if (itemIndex != null) {
                        val editItemRoute = AppRoute.Item(ItemScreenArgs.Edit(itemIndex))
                        outputState = inputState.withNewRoute(stackIndex = 0, route = editItemRoute)
                    }
                }
            }
        }
        return outputState
    }
}