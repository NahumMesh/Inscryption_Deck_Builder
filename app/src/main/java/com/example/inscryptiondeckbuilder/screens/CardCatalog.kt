package com.example.inscryptiondeckbuilder.screens

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore


@Composable
fun CardCatalog() {
    val db = Firebase.firestore

    var imageUrlList by remember { mutableStateOf<List<String>>(emptyList()) }

    db.collection("cards")
        .get()
        .addOnSuccessListener { cards ->
            val urls = mutableListOf<String>()

            for (card in cards) {
                val imageUrlField = card.getString("card_image_file_name")
                imageUrlField?.let {
                    urls.add(it)
                }
            }
            imageUrlList = urls
        }
        .addOnFailureListener { exception ->
            Log.w(TAG, "Error getting documents.", exception)
        }

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp),
        verticalArrangement = Arrangement.spacedBy(3.dp),
        horizontalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        items(imageUrlList) { imageUrl ->
            Row(
                modifier = Modifier
                    .background(color = Color.DarkGray)
                    .fillMaxWidth()
                    .padding((5.dp))
            ) {
                AsyncImage(
                    model = imageUrl,
                    filterQuality = FilterQuality.None,
                    contentDescription = "Inscryption Card Image"
                )
            }
        }
    }
}

