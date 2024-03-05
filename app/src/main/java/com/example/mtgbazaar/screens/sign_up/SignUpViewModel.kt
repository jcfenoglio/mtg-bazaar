package com.example.mtgbazaar.screens.sign_up

import androidx.compose.runtime.mutableStateOf
import com.example.mtgbazaar.common.ext.*
import com.example.mtgbazaar.common.snackbar.SnackbarManager
import com.example.mtgbazaar.model.service.AccountService
import com.example.mtgbazaar.model.service.LogService
import com.example.mtgbazaar.screens.MTGBazaarViewModel
import com.example.mtgbazaar.R.string as AppText
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val accountService: AccountService,
    logService: LogService
) : MTGBazaarViewModel(logService) {
    var uiState = mutableStateOf(SignUpUiState())
        private set

    private val email
        get() = uiState.value.email
    private val password
        get() = uiState.value.password

    fun onEmailChange(newValue: String) {
        uiState.value = uiState.value.copy(email = newValue)
    }

    fun onPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(password = newValue)
    }

    fun onRepeatPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(repeatPassword = newValue)
    }

    fun onSignUpClick(openAndPopUp: (String, String) -> Unit) {
        if (!email.isValidEmail()) {
            SnackbarManager.showMessage(AppText.email_error)
            return
        }

        if (!password.isValidPassword()) {
            SnackbarManager.showMessage(AppText.password_error)
            return
        }

        if (!password.passwordMatches(uiState.value.repeatPassword)) {
            SnackbarManager.showMessage(AppText.password_match_error)
            return
        }

        launchCatching {
            // TODO
        }
    }
}