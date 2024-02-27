package com.example.mtgbazaar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mtgbazaar.ui.theme.MTGBazaarTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MTGBazaarTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BinderList(listOf("Trade Binder", "LotR collection", "legacy piles"))
                }
            }
        }
    }
}


@Composable
fun BinderList(binders: List<String>) {
    LazyColumn {
        items(binders,  key = { it.hashCode() }) { binder ->
            Text(
                text = "$binder",
                modifier = Modifier
                    .padding(10.dp, 5.dp)
            )
        }
    }
}


@Preview
@Composable
fun PreviewBinderList() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ){
        BinderList(binders = listOf("Trade Binder", "LotR collection", "Legacy Elves"))
    }
}
