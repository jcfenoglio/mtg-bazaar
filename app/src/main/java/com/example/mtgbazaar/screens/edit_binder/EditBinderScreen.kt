package com.example.mtgbazaar.screens.edit_binder

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mtgbazaar.common.composable.ActionToolbar
import com.example.mtgbazaar.common.composable.BasicField
import com.example.mtgbazaar.common.composable.CardSelector
import com.example.mtgbazaar.common.ext.card
import com.example.mtgbazaar.common.ext.fieldModifier
import com.example.mtgbazaar.common.ext.spacer
import com.example.mtgbazaar.common.ext.toolbarActions
import com.example.mtgbazaar.model.Binder
import com.example.mtgbazaar.ui.theme.MTGBazaarTheme
import com.example.mtgbazaar.R.string as AppText
import com.example.mtgbazaar.R.drawable as AppIcon

@Composable
fun EditBinderScreen(
    popUpScreen: () -> Unit,
    viewModel: EditBinderViewModel = hiltViewModel()
) {
    val binder by viewModel.binder

    EditBinderScreenContent(
        binder = binder,
        onDoneClick = { viewModel.onDoneClick(popUpScreen) },
        onNameChange = viewModel::onNameChange,
        onDescriptionChange = viewModel::onDescriptionChange,
        onTradeableToggle = viewModel::onTradeableToggle
    )
}

@Composable
fun EditBinderScreenContent(
    modifier: Modifier = Modifier,
    binder: Binder,
    onDoneClick: () -> Unit,
    onNameChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onTradeableToggle: (String) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ActionToolbar(
            title = AppText.edit_binder,
            endActionIcon = AppIcon.ic_check,
            modifier = Modifier.toolbarActions(),
            endAction = { onDoneClick() }
        )

        Spacer(modifier = Modifier.spacer())

        val fieldModifier = Modifier.fieldModifier()
        BasicField(AppText.name, binder.name, onNameChange, fieldModifier)
        BasicField(AppText.description, binder.description, onDescriptionChange, fieldModifier)

        Spacer(modifier = Modifier.spacer())

        val flagSelection = EditTradeFlagOption.getByCheckedState(binder.isTradeable).name
        CardSelector(
            label = AppText.tradeable,
            options = EditTradeFlagOption.getOptions(),
            selection = flagSelection,
            modifier = Modifier.card()) {
            newValue -> onTradeableToggle(newValue)
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun EditBinderScreenPreview() {
    val binder = Binder(
        name = "Binder Name",
        description = "Binder description",
        isTradeable = true
    )

    MTGBazaarTheme {
        EditBinderScreenContent(
            binder = binder,
            onDoneClick = { },
            onNameChange = { },
            onDescriptionChange = { },
            onTradeableToggle = { }
        )
    }
}