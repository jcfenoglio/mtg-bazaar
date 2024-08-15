package com.example.mtgbazaar.screens.collection

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mtgbazaar.model.Binder
import com.example.mtgbazaar.ui.theme.MTGBazaarTheme

@Composable
fun BinderItem (
    binder: Binder,
    options: List<String>,
    onActionClick: () -> Unit
) {
   Card(
       onClick = onActionClick,
       colors = CardDefaults.cardColors(contentColor = MaterialTheme.colorScheme.primaryContainer),
       modifier = Modifier.fillMaxWidth(),

   ) {
        Column {
            Text(
                modifier = Modifier.padding(4.dp, 4.dp, 4.dp, 2.dp),
                text = binder.name,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )

            Text(
                modifier = Modifier.padding(4.dp, 0.dp, 4.dp, 4.dp),
                text = binder.description,
                color = MaterialTheme.colorScheme.tertiary,
                fontSize = 12.sp
            )
        }
   }
}

@Preview(
    showBackground = true
)
@Composable
fun BinderItemPreview () {
    val binder = Binder(
        name = "Binder Name",
        description = "Binder description",
        tradeable = true
    )

    MTGBazaarTheme {
        BinderItem(binder = binder, options = emptyList(), onActionClick = { })
    }
}