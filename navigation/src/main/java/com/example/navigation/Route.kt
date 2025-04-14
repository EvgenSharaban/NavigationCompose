package com.example.navigation

import android.os.Parcelable
import androidx.compose.runtime.Immutable

@Immutable
interface Route : Parcelable {
    val screenProducer: () -> Screen
}