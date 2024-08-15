package com.example.mtgbazaar.screens.binder

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import com.example.mtgbazaar.BINDER_ID
import com.example.mtgbazaar.EDIT_BINDER_SCREEN
import com.example.mtgbazaar.common.ext.idFromParameter
import com.example.mtgbazaar.model.Binder
import com.example.mtgbazaar.model.MagicCard
import com.example.mtgbazaar.model.service.LogService
import com.example.mtgbazaar.model.service.StorageService

import com.example.mtgbazaar.screens.MTGBazaarViewModel
import javax.inject.Inject

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.emptyFlow

@HiltViewModel
class BinderViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    logService:LogService,
    private val storageService: StorageService
) : MTGBazaarViewModel(logService){
    val binder = mutableStateOf(Binder())
    val binderCards = emptyFlow<List<MagicCard>>()

    init {
        val binderId = savedStateHandle.get<String>(BINDER_ID)
        if (binderId != null) {
            launchCatching {
                binder.value = storageService.getBinder(binderId.idFromParameter()) ?: Binder()
            }
        }
    }

    fun onEditClick(openScreen: (String) -> Unit, binder: Binder) {
        openScreen("$EDIT_BINDER_SCREEN?$BINDER_ID={${binder.id}}")
    }

    fun onBackClick(popUpScreen: () -> Unit) {
        launchCatching {
            popUpScreen()
        }
    }

    fun onMagicCardActionClick(openScreen: (String) -> Unit, magicCard: MagicCard) {

    }
}