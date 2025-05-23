package com.example.navigationtest.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.navigationtest.R
import com.example.navigationtest.ui.AppScreen
import com.example.navigationtest.ui.AppScreenEnvironment
import com.example.navigationtest.ui.DropdownItem
import com.example.navigationtest.ui.scaffold.showToast

val ProfileScreenProducer = { ProfileScreen() }

class ProfileScreen : AppScreen {
    override val environment = AppScreenEnvironment().apply {
        titleRes = R.string.profile
        icon = Icons.Default.AccountBox
        dropdownList = listOf(
            DropdownItem(
                name = R.string.about,
                onClick = { context ->
                    showToast(context, R.string.scaffold_app)
                }
            )
        )
    }

    @Composable
    override fun Content() {
        Text(
            text = "Profile screen",
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentHeight()
        )
    }
}