package com.example.navigationtest.ui.screens.items

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.navigation.LocalRouter
import com.example.navigation.ResponseListener
import com.example.navigation.Router
import com.example.navigationtest.ItemsRepository
import com.example.navigationtest.R
import com.example.navigationtest.ui.AppRoute
import com.example.navigationtest.ui.AppScreen
import com.example.navigationtest.ui.AppScreenEnvironment
import com.example.navigationtest.ui.DropdownItem
import com.example.navigationtest.ui.FloatingAction
import com.example.navigationtest.ui.scaffold.showToast
import com.example.navigationtest.ui.screens.item.ItemScreenArgs

val ItemsScreenProducer = { ItemsScreen() }

class ItemsScreen : AppScreen {
    private var router: Router? = null

    override val environment = AppScreenEnvironment().apply {
        titleRes = R.string.items
        icon = Icons.AutoMirrored.Filled.List
        floatingAction = FloatingAction(
            icon = Icons.Default.Add,
            onClick = {
                router?.launch(AppRoute.Item(ItemScreenArgs.Add))
            }
        )
        dropdownList = listOf(
            DropdownItem(
                name = R.string.about,
                onClick = { context ->
                    showToast(context, R.string.scaffold_app)
                }
            ),
            DropdownItem(
                name = R.string.clear,
                onClick = {
                    clearList()
                }
            )
        )
    }

    @Composable
    override fun Content() {
        router = LocalRouter.current

//        val itemsRepository = ItemsRepository.get()
        val viewModel = viewModel<ItemsViewModel>()
        val items by viewModel.itemsFlow.collectAsStateWithLifecycle()
        val isEmpty by remember {
            derivedStateOf { items.isEmpty() }
        }
        ResponseListener(viewModel::processResponse)
        ItemsContent(
            isItemsEmpty = isEmpty,
            items = { items },
            onItemClicked = { index ->
                router?.launch(AppRoute.Item(ItemScreenArgs.Edit(index)))
            }
        )
    }
}

@Composable
fun ItemsContent(
    isItemsEmpty: Boolean,
    items: () -> List<String>,
    onItemClicked: (Int) -> Unit,
) {
    if (isItemsEmpty) {
        Text(
            text = stringResource(R.string.no_items),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentHeight()
        )
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            val itemsList = items()
            items(itemsList.size) { index ->
                Text(
                    text = itemsList[index],
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onItemClicked(index)
                        }
                        .padding(all = 8.dp)
                )
            }
        }
    }
}

private fun clearList() {
    val itemsRepository: ItemsRepository = ItemsRepository.get()
    itemsRepository.clear()
}