package com.example.mtgbazaar.model.service

interface ConfigurationService {
    suspend fun fetchConfiguration(): Boolean
    val isShowBinderEditButtonConfig: Boolean
}