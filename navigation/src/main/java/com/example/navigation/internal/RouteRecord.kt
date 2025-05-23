package com.example.navigation.internal

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import com.example.navigation.Route
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Immutable
@Parcelize
internal data class RouteRecord(
    val uuid: String,
    val route: Route
) : Parcelable {

    constructor(route: Route) : this(
        uuid = UUID.randomUUID().toString(),
        route = route
    )
}
