package com.example.mtgbazaar.screens.settings

import com.example.mtgbazaar.LOGIN_SCREEN
import com.example.mtgbazaar.SIGN_UP_SCREEN
import com.example.mtgbazaar.SPLASH_SCREEN
import com.example.mtgbazaar.model.service.AccountService
import com.example.mtgbazaar.model.service.LogService
import com.example.mtgbazaar.model.service.StorageService
import com.example.mtgbazaar.screens.MTGBazaarViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    logService: LogService,
    private val accountService: AccountService,
    private val storageService: StorageService
) : MTGBazaarViewModel(logService) {
    val uiState = accountService.currentUser.map { SettingsUiState(it.isAnonymous) }

    fun onLoginClick(openScreen: (String) -> Unit) = openScreen(LOGIN_SCREEN)

    fun onSignUpClick(openScreen: (String) -> Unit) = openScreen(SIGN_UP_SCREEN)

    fun onSignOutClick(restartApp: (String) -> Unit) {
        launchCatching {
            accountService.signOut()
            restartApp(SPLASH_SCREEN)
        }
    }

    fun onDeleteMyAccountClick(restartApp: (String) -> Unit) {
        launchCatching {
            accountService.deleteAccount()
            restartApp(SPLASH_SCREEN)
        }
    }
}
