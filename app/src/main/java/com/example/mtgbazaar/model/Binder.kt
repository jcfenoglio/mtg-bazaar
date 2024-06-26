package com.example.mtgbazaar.model

import com.google.firebase.firestore.DocumentId

data class Binder(
    @DocumentId val id: String = "",
    val name: String = "",
    val description: String = "",
    val tradeable: Boolean = false,
    val userId: String = ""
)
