package com.example.mtgbazaar.screens.collection

import androidx.compose.runtime.mutableStateOf
import com.example.mtgbazaar.BINDER_ID
import com.example.mtgbazaar.BINDER_SCREEN
import com.example.mtgbazaar.EDIT_BINDER_SCREEN
import com.example.mtgbazaar.SETTINGS_SCREEN
import com.example.mtgbazaar.model.Binder
import com.example.mtgbazaar.model.service.ConfigurationService
import com.example.mtgbazaar.model.service.LogService
import com.example.mtgbazaar.model.service.StorageService
import com.example.mtgbazaar.screens.MTGBazaarViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CollectionViewModel @Inject constructor(
    logService: LogService,
    private val storageService: StorageService,
    private val configurationService: ConfigurationService
) : MTGBazaarViewModel(logService){
    val options = mutableStateOf<List<String>>(listOf())

    val collection = storageService.binders

    fun loadBinderOptions() {
        // TODO
    }

    fun onAddClick(openScreen: (String) -> Unit) = openScreen(EDIT_BINDER_SCREEN)

    fun onSettingsClick(openScreen: (String) -> Unit) = openScreen(SETTINGS_SCREEN)

    fun onBinderActionClick(openScreen: (String) -> Unit, binder: Binder) {
        openScreen("$BINDER_SCREEN?$BINDER_ID={${binder.id}}")
    }

    private fun onDeleteBinderClick(binder: Binder) {
        launchCatching { storageService.deleteBinder(binder.id) }
    }
}