package com.example.navigation.internal

import com.example.navigation.ScreenResponseReceiver
import kotlin.reflect.KClass

internal object EmptyScreenResponseReceiver : ScreenResponseReceiver {
    override fun <T : Any> get(clazz: KClass<T>): T? = null
}