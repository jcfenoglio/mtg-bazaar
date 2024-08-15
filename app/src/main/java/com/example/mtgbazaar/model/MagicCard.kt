package com.example.mtgbazaar.model

import com.google.firebase.firestore.DocumentId

data class MagicCard (
    @DocumentId val id: String = "",
    val name: String = "",
    val cost: String = "",
    val condition: String = "",
    val price: Float = 0.0f,
    val multiverseId: String = "",
    val binderId: String = ""
)
