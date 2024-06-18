package com.example.mtgbazaar.screens.edit_binder

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import com.example.mtgbazaar.BINDER_ID
import com.example.mtgbazaar.common.ext.idFromParameter
import com.example.mtgbazaar.model.Binder
import com.example.mtgbazaar.model.service.LogService
import com.example.mtgbazaar.model.service.StorageService
import com.example.mtgbazaar.screens.MTGBazaarViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditBinderViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    logService: LogService,
    private val storageService: StorageService,
) : MTGBazaarViewModel(logService) {
    val binder = mutableStateOf(Binder())

    init {
        val binderId = savedStateHandle.get<String>(BINDER_ID)
        if (binderId != null) {
            launchCatching {
                binder.value = storageService.getBinder(binderId.idFromParameter()) ?: Binder()
            }
        }
    }

    fun onNameChange(newValue: String) {
        binder.value = binder.value.copy(name = newValue)
    }

    fun onDescriptionChange(newValue: String) {
        binder.value = binder.value.copy(description = newValue)
    }

    fun onTradeableToggle(newValue: String) {
        val newFlagOption = EditTradeFlagOption.getBooleanValue(newValue)
        binder.value = binder.value.copy(tradeable = newFlagOption)
    }

    fun onDoneClick(popUpScreen: () -> Unit) {
        launchCatching {
            val editedBinder = binder.value
            if (editedBinder.id.isBlank()) {
                storageService.saveBinder(editedBinder)
            } else {
                storageService.updateBinder(editedBinder)
            }
            popUpScreen()
        }
    }
}