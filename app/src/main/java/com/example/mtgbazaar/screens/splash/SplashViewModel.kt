package com.example.mtgbazaar.screens.splash

import androidx.compose.runtime.mutableStateOf
import com.example.mtgbazaar.COLLECTION_SCREEN
import com.example.mtgbazaar.SPLASH_SCREEN
import com.example.mtgbazaar.model.service.AccountService
import com.example.mtgbazaar.model.service.ConfigurationService
import com.example.mtgbazaar.model.service.LogService
import com.example.mtgbazaar.screens.MTGBazaarViewModel
import com.google.firebase.auth.FirebaseAuthException
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    configurationService: ConfigurationService,
    private val accountService: AccountService,
    logService: LogService
): MTGBazaarViewModel(logService) {
    val showError = mutableStateOf(false)

    init {
        launchCatching { configurationService.fetchConfiguration() }
    }

    fun onAppStart(openAndPopUp: (String, String) -> Unit) {
        showError.value = false
        if(accountService.hasUser) openAndPopUp(COLLECTION_SCREEN, SPLASH_SCREEN)
        else createAnonymousAccount(openAndPopUp)
    }

    private fun createAnonymousAccount(openAndPopUp: (String, String) -> Unit) {
        launchCatching(snackbar = false) {
            try {
                accountService.createAnonymousAccount()
            } catch (ex: FirebaseAuthException) {
                showError.value = true
                throw ex
            }
            openAndPopUp(COLLECTION_SCREEN, SPLASH_SCREEN)
        }
    }
}