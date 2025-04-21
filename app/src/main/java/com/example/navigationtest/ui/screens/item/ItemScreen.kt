package com.example.navigationtest.ui.screens.item

import android.os.Parcelable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.navigation.LocalRouter
import com.example.navigationtest.R
import com.example.navigationtest.ui.AppRoute
import com.example.navigationtest.ui.AppScreen
import com.example.navigationtest.ui.AppScreenEnvironment
import com.example.navigationtest.ui.DropdownItem
import com.example.navigationtest.ui.scaffold.showToast
import kotlinx.parcelize.Parcelize

fun itemScreenProducer(args: ItemScreenArgs): () -> ItemScreen {
    return { ItemScreen(args) }
}

sealed class ItemScreenArgs : Parcelable {
    @Parcelize
    data object Add : ItemScreenArgs()

    @Parcelize
    data class Edit(val index: Int) : ItemScreenArgs()
}

data class ItemScreenResponse(
    val args: ItemScreenArgs,
    val newValue: String,
)

class ItemScreen(
    private val args: ItemScreenArgs,
) : AppScreen {
    override val environment = AppScreenEnvironment().apply {
        titleRes = if (args is ItemScreenArgs.Add) {
            R.string.add_item
        } else {
            R.string.edit_item
        }
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
        val viewModel = viewModel { ItemViewModel(args) }
        val router = LocalRouter.current
        ItemContent(
            initialValue = remember { viewModel.getInitialValue() },
            isAddMode = args is ItemScreenArgs.Add,
            onSubmitNewItem = { newValue ->
                router.pop(ItemScreenResponse(args, newValue))
            },
            onLaunchSettingsScreen = {
                router.launch(AppRoute.Tab.Settings)
            }
        )
    }
}

@Composable
fun ItemContent(
    initialValue: String = "",
    isAddMode: Boolean = false,
    onSubmitNewItem: (String) -> Unit,
    onLaunchSettingsScreen: () -> Unit
) {
    var currentItemValue by rememberSaveable { mutableStateOf(initialValue) }
    val isAddEnabled by remember {
        derivedStateOf { currentItemValue.isNotEmpty() }
    }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        OutlinedTextField(
            value = currentItemValue,
            label = { Text(stringResource(R.string.enter_new_value)) },
            singleLine = true,
            onValueChange = { enteredValue ->
                currentItemValue = enteredValue
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            enabled = isAddEnabled,
            onClick = { onSubmitNewItem(currentItemValue) }
        ) {
            val buttonText = if (isAddMode) {
                R.string.add_new_item
            } else {
                R.string.edit_item
            }
            Text(text = stringResource(buttonText))
        }


        Button(
            onClick = onLaunchSettingsScreen
        ) {
            Text(text = "Launch Settings")
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun ItemPreview() {
    ItemContent(onSubmitNewItem = {}, onLaunchSettingsScreen = {})
}