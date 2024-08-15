package com.example.mtgbazaar.screens.binder

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mtgbazaar.model.MagicCard

@Composable
fun MagicCardItem (
    magicCard: MagicCard,
    onActionClick: () -> Unit
) {
    Card(
        onClick = onActionClick,
        colors = CardDefaults.cardColors(contentColor = MaterialTheme.colorScheme.primaryContainer),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            Text(
                modifier = Modifier.padding(4.dp, 4.dp, 4.dp, 2.dp),
                text = magicCard.name,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp)
        }
    }
}