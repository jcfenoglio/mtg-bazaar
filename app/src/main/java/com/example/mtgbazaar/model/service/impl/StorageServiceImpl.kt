package com.example.mtgbazaar.model.service.impl

import com.example.mtgbazaar.model.Binder
import com.example.mtgbazaar.model.MagicCard
import com.example.mtgbazaar.model.service.AccountService
import com.example.mtgbazaar.model.service.StorageService
import com.example.mtgbazaar.model.service.trace
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.dataObjects
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class StorageServiceImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: AccountService
) : StorageService {
    override val binders: Flow<List<Binder>>
        get() =
            auth.currentUser.flatMapLatest {user ->
                firestore.collection(BINDER_COLLECTION).whereEqualTo(USER_ID_FIELD, user.id).dataObjects()
            }

    override suspend fun getBinder(binderId: String): Binder? =
        firestore.collection(BINDER_COLLECTION).document(binderId).get().await().toObject()

    override suspend fun saveBinder(binder: Binder): String =
        trace(SAVE_BINDER_TRACE) {
            val binderWithUserId = binder.copy(userId = auth.currentUserId)
            firestore.collection(BINDER_COLLECTION).add(binderWithUserId).await().id
        }

    override suspend fun updateBinder(binder: Binder): Unit =
        trace(UPDATE_BINDER_TRACE) {
            firestore.collection(BINDER_COLLECTION).document(binder.id).set(binder).await()
        }

    override suspend fun deleteBinder(binderId: String) {
        firestore.collection(BINDER_COLLECTION).document(binderId).delete().await()
    }

    override val binderCards: Flow<List<MagicCard>>
        get() = TODO("Not yet implemented")

    companion object {
        private const val USER_ID_FIELD = "userId"
        private const val BINDER_COLLECTION = "binders"
        private const val SAVE_BINDER_TRACE = "saveBinder"
        private const val UPDATE_BINDER_TRACE = "updateBinder"
    }
}