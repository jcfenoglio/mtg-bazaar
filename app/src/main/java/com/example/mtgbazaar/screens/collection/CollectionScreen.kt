package com.example.mtgbazaar.screens.collection

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mtgbazaar.common.composable.ActionToolbar
import com.example.mtgbazaar.common.ext.toolbarActions
import com.example.mtgbazaar.model.Binder
import com.example.mtgbazaar.ui.theme.MTGBazaarTheme
import com.example.mtgbazaar.R.string as AppText
import com.example.mtgbazaar.R.drawable as AppIcon

@Composable
fun CollectionScreen(
    openScreen: (String) -> Unit,
    viewModel: CollectionViewModel = hiltViewModel()
) {
    // TODO: fix this once the dependency moves to stable branch
    val collection = viewModel.collection.collectAsStateWithLifecycle(emptyList(), lifecycleOwner = androidx.compose.ui.platform.LocalLifecycleOwner.current)

    CollectionScreenContent(
        collection = collection.value,
        onAddClick = viewModel::onAddClick,
        onSettingsClick = viewModel::onSettingsClick,
        onBinderActionClick = viewModel::onBinderActionClick,
        openScreen = openScreen
    )

    LaunchedEffect(viewModel) { viewModel.loadBinderOptions() }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CollectionScreenContent(
    modifier: Modifier = Modifier,
    collection: List<Binder>,
    onAddClick: ((String) -> Unit) -> Unit,
    onSettingsClick: ((String) -> Unit) -> Unit,
    onBinderActionClick: ((String) -> Unit, Binder) -> Unit,
    openScreen: (String) -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onAddClick(openScreen) },
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSecondary,
                modifier = modifier.padding(16.dp)
            ) {
                Icon(Icons.Filled.Add, "Add")
            }
        }
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()) {
            ActionToolbar(
                title = AppText.collection_title,
                modifier = Modifier.toolbarActions(),
                endActionIcon = AppIcon.ic_settings,
                endAction = { onSettingsClick(openScreen) }
            )

            LazyColumn {
                items(items = collection, key = { it.id }) { binderItem ->
                    BinderItem(
                        binder = binderItem,
                        options = listOf(),
                        onActionClick = { onBinderActionClick(openScreen, binderItem) }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CollectionScreenPreview() {
    val binder = Binder(
        id = "1",
        name = "Test Binder",
        description = "This is a test binder with real cards",
        tradeable = false
    )
    val binder2 = Binder(
        id = "2",
        name = "Test Binder",
        description = "This is a test binder with real cards",
        tradeable = false
    )

    MTGBazaarTheme {
        CollectionScreenContent(
            collection = listOf(binder, binder2),
            onAddClick = { },
            onSettingsClick = { },
            onBinderActionClick = { _, _ -> },
            openScreen = { }
        )
    }
}
