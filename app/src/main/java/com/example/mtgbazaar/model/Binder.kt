package com.example.mtgbazaar.model

import com.google.firebase.firestore.DocumentId

data class Binder(
    @DocumentId val id: String = "",
    val title: String = "",
    val description: String = "",
    val isTradeable: Boolean = false,
    val userId: String = ""
)
