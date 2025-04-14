package com.example.navigationtest.ui

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.navigation.Screen
import com.example.navigation.ScreenEnvironment
import com.example.navigationtest.R

@Stable
interface AppScreen : Screen {
    override val environment: AppScreenEnvironment
}

@Stable
class AppScreenEnvironment : ScreenEnvironment {
    var titleRes: Int by mutableStateOf(R.string.app_name)
    var icon: ImageVector? by mutableStateOf(null)
    var floatingAction: FloatingAction? by mutableStateOf(null)
    var dropdownList: List<DropdownItem> = mutableStateListOf<DropdownItem>()
}

@Immutable
data class FloatingAction(
    val icon: ImageVector,
    val onClick: () -> Unit,
)

@Immutable
data class DropdownItem(
    @StringRes val name: Int,
    val onClick: (Context) -> Unit
)