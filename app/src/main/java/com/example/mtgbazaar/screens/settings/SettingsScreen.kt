package com.example.mtgbazaar.screens.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mtgbazaar.common.composable.BasicToolbar
import com.example.mtgbazaar.common.composable.DangerousCardEditor
import com.example.mtgbazaar.common.composable.DialogCancelButton
import com.example.mtgbazaar.common.composable.DialogConfirmButton
import com.example.mtgbazaar.common.composable.RegularCardEditor
import com.example.mtgbazaar.common.ext.card
import com.example.mtgbazaar.common.ext.spacer
import com.example.mtgbazaar.ui.theme.MTGBazaarTheme
import com.example.mtgbazaar.R.string as AppText
import com.example.mtgbazaar.R.drawable as AppIcon


@Composable
fun SettingsScreen(
    restartApp: (String) -> Unit,
    openScreen: (String) -> Unit,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState(initial = SettingsUiState(false))
    
    SettingsScreenContent(
        uiState = uiState,
        onLoginClick = { viewModel.onLoginClick(openScreen) },
        onSignUpClick = { viewModel.onSignUpClick(openScreen) },
        onSignOutClick = { viewModel.onSignOutClick(restartApp) },
        onDeleteMyAccountClick = { viewModel.onDeleteMyAccountClick(restartApp) }
    )
}

@Composable
fun SettingsScreenContent(
    modifier: Modifier = Modifier,
    uiState: SettingsUiState,
    onLoginClick: () -> Unit,
    onSignUpClick: () -> Unit,
    onSignOutClick: () -> Unit,
    onDeleteMyAccountClick: () -> Unit
) {
    Column(
        modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BasicToolbar(AppText.settings)

        Spacer(modifier = Modifier.spacer())

        if (uiState.isAnonymousAccount) {
            RegularCardEditor(AppText.sign_in, AppIcon.ic_sign_in, "", Modifier.card()) {
                onLoginClick()
            }

            RegularCardEditor(AppText.create_account, AppIcon.ic_create_account, "", Modifier.card()) {
                onSignUpClick()
            }
        } else {
            SignOutCard { onSignOutClick() }
            DeleteMyAccountCard { onDeleteMyAccountClick() }
        }
    }
}

@Composable
private fun SignOutCard(signOut: () -> Unit) {
    var showWarningDialog by remember { mutableStateOf(false) }

    RegularCardEditor(AppText.sign_out, AppIcon.ic_exit, "", modifier = Modifier.card()) {
        showWarningDialog = true
    }

    if  (showWarningDialog) {
        AlertDialog(
            onDismissRequest =  { showWarningDialog = false },
            confirmButton = {
                DialogConfirmButton(AppText.sign_out) {
                    signOut()
                    showWarningDialog = false
                }
            },
            title = { Text(stringResource(AppText.sign_out_dialog_title)) },
            text = { Text(stringResource(AppText.sign_out_dialog_description))},
            dismissButton = { DialogCancelButton(AppText.cancel) { showWarningDialog = false } }
        )
    }
}


@Composable
private fun DeleteMyAccountCard(deleteMyAccount: () -> Unit) {
    var showWarningDialog by remember { mutableStateOf(false) }

    DangerousCardEditor(
        AppText.delete_my_account,
        AppIcon.ic_delete_my_account,
        "",
        Modifier.card()
    ) {
        showWarningDialog = true
    }

    if (showWarningDialog) {
        AlertDialog(
            onDismissRequest = { showWarningDialog = false },
            confirmButton =  {
                DialogConfirmButton(AppText.delete_my_account) {
                    deleteMyAccount()
                    showWarningDialog = false
                }
            },
            title = { Text(stringResource(AppText.delete_account_dialog_title)) },
            text = { Text(stringResource(AppText.delete_account_dialog_description)) },
            dismissButton = { DialogCancelButton(AppText.cancel) { showWarningDialog = false }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    val uiState = SettingsUiState(isAnonymousAccount = false)

    MTGBazaarTheme {
        SettingsScreenContent(
            uiState = uiState,
            onLoginClick = { },
            onSignUpClick = { },
            onSignOutClick = { },
            onDeleteMyAccountClick = { }
        )
    }
}