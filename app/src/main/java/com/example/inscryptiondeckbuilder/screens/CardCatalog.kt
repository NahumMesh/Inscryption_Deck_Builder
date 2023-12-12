package com.example.inscryptiondeckbuilder.screens

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

/*
@SuppressLint("UnrememberedMutableState")
@Composable
fun CardCatalog(navController: NavHostController) {
    val db = Firebase.firestore
    val cardDataList by remember { mutableStateOf(mutableStateListOf<Card>())}

    db.collection("cards")
        .get()
        .addOnSuccessListener { cards ->
                for (card in cards) {
                    val image = card.data["card_image_file_name"].toString()
                    val id = card.id
                    val name = card.data["card_name"].toString()
                    val cost = card.data["cost"].toString()
                    val health = card.data["health"]
                    val power = card.data["power"]
                    cardDataList.add(Card(id, image, name, cost, health, power))
            }
        }
        .addOnFailureListener { exception ->
            Log.w(TAG, "Error getting documents.", exception)
        }

        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 128.dp),
            verticalArrangement = Arrangement.spacedBy(3.dp),
            horizontalArrangement = Arrangement.spacedBy(3.dp),
            contentPadding = PaddingValues(top = 75.dp, bottom = 90.dp)
        ) {
            items(cardDataList ) { data ->
                ColumnItem(
                    imageUrl = data.card_image_file_name,
                    itemIndex = data.id,
                    navController = navController
                )

            }
        }
}

@Composable
fun ColumnItem(
imageUrl: String,
itemIndex: String,
navController: NavController
){
    Row(
        modifier = Modifier
            .background(color = Color.DarkGray)
            .fillMaxWidth()
            .padding((5.dp))
            .clickable {
                navController.navigate(route = "card_data/$itemIndex")
            }
    ) {
        AsyncImage(
            model = imageUrl,
            filterQuality = FilterQuality.None,
            contentDescription = "Inscryption Card Image"
        )
    }
}

*/
