package com.example.mtgbazaar.model

import com.google.firebase.firestore.DocumentId

data class MagicCard (
    @DocumentId val id: String = "",
    val name: String = "",
    val binderId: String = ""
)
