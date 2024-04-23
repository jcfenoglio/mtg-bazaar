package com.example.mtgbazaar.screens.collection

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import com.example.mtgbazaar.common.composable.ActionToolbar
import com.example.mtgbazaar.common.ext.smallSpacer
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
    CollectionScreenContent(
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
    onAddClick: ((String) -> Unit) -> Unit,
    onSettingsClick: ((String) -> Unit) -> Unit,
    onBinderActionClick: ((String) -> Unit, Binder, String) -> Unit,
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

            Spacer(modifier = Modifier.smallSpacer())

            LazyColumn {
                items(items = emptyList<Binder>(), key = { it.id }) { binderItem ->
                    BinderItem(
                        binder = binderItem,
                        options = listOf(),
                        onActionClick = { action -> onBinderActionClick(openScreen, binderItem, action) }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CollectionScreenPreview() {
    MTGBazaarTheme {
        CollectionScreenContent(
            onAddClick = { },
            onSettingsClick = { },
            onBinderActionClick = { _, _, _ -> },
            openScreen = { }
        )
    }
}
