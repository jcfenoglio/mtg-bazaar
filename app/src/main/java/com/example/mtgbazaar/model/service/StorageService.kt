package com.example.mtgbazaar.model.service

import com.example.mtgbazaar.model.Binder
import kotlinx.coroutines.flow.Flow

interface StorageService {
    val binders: Flow<List<Binder>>
    suspend fun getBinder(binderId: String): Binder?
    suspend fun saveBinder(binder: Binder): String
    suspend fun updateBinder(binder: Binder)
    suspend fun deleteBinder(binderId: String)
}