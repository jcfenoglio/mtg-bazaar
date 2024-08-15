package com.example.mtgbazaar.screens.binder

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Scaffold
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mtgbazaar.common.composable.ActionTextToolbar
import com.example.mtgbazaar.common.ext.toolbarActions
import com.example.mtgbazaar.model.Binder
import com.example.mtgbazaar.model.MagicCard
import com.example.mtgbazaar.R.drawable as AppIcon

@Composable
fun BinderScreen(
    openScreen: (String) -> Unit,
    popUpScreen: () -> Unit,
    viewModel: BinderViewModel = hiltViewModel()
) {
    val binder by viewModel.binder
    // TODO: fix this once the dependency moves to stable branch
    val binderCards = viewModel.binderCards.collectAsStateWithLifecycle(emptyList<MagicCard>(), lifecycleOwner = androidx.compose.ui.platform.LocalLifecycleOwner.current)

    BinderScreenContent(
        binder = binder,
        binderCards = binderCards.value,
        onBackClick = { viewModel.onBackClick(popUpScreen) },
        onEditClick = viewModel::onEditClick,
        onMagicCardActionClick = viewModel::onMagicCardActionClick,
        openScreen = openScreen
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BinderScreenContent(
    modifier: Modifier = Modifier,
    binder: Binder,
    binderCards: List<MagicCard>,
    onBackClick: () -> Unit,
    onEditClick: ((String) -> Unit, Binder) -> Unit,
    onMagicCardActionClick: ((String) -> Unit, MagicCard) -> Unit,
    openScreen: (String) -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /*TODO*/ },
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
            ActionTextToolbar(
                title = binder.name,
                endActionIcon = AppIcon.ic_edit_pencil,
                navActionIcon = AppIcon.ic_back_arrow,
                modifier = Modifier.toolbarActions(),
                endAction = { onEditClick(openScreen, binder) },
                navAction = { onBackClick() }
            )

            LazyColumn {
                items(items = binderCards, key = { it.id }) { magicCard ->
                    MagicCardItem(
                        magicCard = magicCard,
                        onActionClick = { onMagicCardActionClick(openScreen, magicCard) }
                    )
                }
            }
        }
    }
}