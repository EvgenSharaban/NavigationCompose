package com.example.navigationtest.ui.scaffold

import android.widget.Toast
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.navigation.Navigation
import com.example.navigationtest.ItemsRepository
import com.example.navigationtest.R
import com.example.navigationtest.ui.AppRoute

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppToolbar(navigation: Navigation) {
    val (router, navigationState) = navigation
    val itemsRepository: ItemsRepository = ItemsRepository.get()

    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(
                    (navigationState.currentRoute as? AppRoute)?.titleRes ?: R.string.app_name
                ),
                fontWeight = FontWeight.Bold
            )
        },
        colors = TopAppBarDefaults.largeTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary
        ),
        navigationIcon = {
            IconButton(
                onClick = {
                    if (!navigationState.isRoot) router.pop()
                }
            ) {
                Icon(
                    imageVector = if (navigationState.isRoot) Icons.Default.Menu else Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null
                )
            }
        },
        actions = {
            var showPopupMenu by remember { mutableStateOf(false) }
            val context = LocalContext.current
            IconButton(
                onClick = { showPopupMenu = true }
            ) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = null
                )
            }
            DropdownMenu(
                expanded = showPopupMenu,
                onDismissRequest = { showPopupMenu = false }
            ) {
                DropdownMenuItem(
                    text = { Text(stringResource(R.string.about)) },
                    onClick = {
                        Toast.makeText(
                            context,
                            R.string.scaffold_app,
                            Toast.LENGTH_LONG
                        ).show()
                        showPopupMenu = false
                    }
                )

                if (navigationState.currentRoute == AppRoute.Tab.Items) {
                    DropdownMenuItem(
                        text = { Text(stringResource(R.string.clear)) },
                        onClick = {
                            itemsRepository.clear()
                            showPopupMenu = false
                        }
                    )
                }
            }
        }
    )
}