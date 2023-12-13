package com.example.inscryptiondeckbuilder.screens

import android.content.ContentValues
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.inscryptiondeckbuilder.BottomBarScreen
import com.example.inscryptiondeckbuilder.data.CardData
import com.example.inscryptiondeckbuilder.data.CardImage
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

/**
 * The DeckScreen Composable function displays a LazyVerticalGrid with the data from the FireStore.
 * Once the card is clicked, it navigates to the DetailDeckScreen.
 */
@Composable
fun DeckScreen(onNavigateToCardScreen: (String) -> Unit) {
    val db = Firebase.firestore
    val deckDataList = mutableStateListOf<CardImage>()

    db.collection("deck")
        .get()
        .addOnSuccessListener { deck ->
            for (card in deck) {
                val image = card.data["card_image_file_name"].toString()
                val id = card.id
                val name = card.data["card_name"].toString()
                deckDataList.add(CardImage(id, image, name))

                Log.d(ContentValues.TAG, "The deck. $deck")
            }
        }
        .addOnFailureListener { exception ->
            Log.w(ContentValues.TAG, "Error getting documents.", exception)
        }

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp),
        verticalArrangement = Arrangement.spacedBy(3.dp),
        horizontalArrangement = Arrangement.spacedBy(3.dp),
        contentPadding = PaddingValues(top = 75.dp, bottom = 90.dp)
    ) {
        items(deckDataList ) { data ->
            Row(
                modifier = Modifier
                    .background(color = Color.DarkGray)
                    .fillMaxWidth()
                    .padding((5.dp))
                    .clickable {
                        onNavigateToCardScreen(data.id)
                    }
            ) {
                AsyncImage(
                    model = data.card_image_file_name,
                    filterQuality = FilterQuality.None,
                    contentDescription = "Inscryption Card Image",
                    modifier = Modifier.padding(top = 15.dp)
                )
            }
            Text(text = data.card_name, color = Color.White,
                fontSize = 15.sp, fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}

/**
 * The ScrapeDeck Composable function scrapes data from the FireStore.
 * It then calls the DetailDeckScreen and passed the values into it.
 */
@Composable
fun ScrapeDeck(cardId: String, navController: NavController) {
    val db = Firebase.firestore
    val deckData by remember { mutableStateOf(mutableStateListOf<CardData>()) }

    db.collection("deck").document(cardId)
        .get()
        .addOnSuccessListener { items ->
            val id = items.id
            val image = items.data?.get("card_image_file_name").toString()
            val names = items.data?.get("card_name").toString()
            val cost = items.data?.get("cost").toString()
            val health = items.data?.get("health") as? Long
            val power = items.data?.get("power") as? Long
            val sigils = items.data?.get("sigils") as? List<HashMap<String, String>>

            deckData.add(CardData(id, image, names, cost, health, power, sigils))
        }
        .addOnFailureListener { exception ->
            Log.w(ContentValues.TAG, "Error getting documents.", exception)
        }

    // Adding the queried data into the DetailDeckScreen.
    for (data in deckData) {
        DetailDeckScreen(
            id = data.id,
            photos = data.card_image_file_name,
            names = data.card_name,
            cost = data.cost,
            health = data.health?.toInt(),
            power = data.power?.toInt(),
            sigils = data.sigils,
            navController = navController
        )
    }
}

/**
 * The writeData function writes the data from the selected card, to the deck collection.
 */
fun writeData(
    photos: String?,
    names: String?,
    cost: String?,
    health: Int?,
    power: Int?,
    sigils: List<HashMap<String, String>>?
){
    val db = FirebaseFirestore.getInstance()

    val data = hashMapOf(
        "card_image_file_name" to photos,
        "card_name" to names,
        "cost" to cost,
        "health" to health,
        "power" to power,
        "sigils" to sigils
    )
    db.collection("deck")
        .add(data)
        .addOnFailureListener { e ->
            Log.w(ContentValues.TAG, "Error getting documents.", e)
        }
}

/**
 * The deleteData function queries the database and deletes the data from the selected card.
 * Once the data is deleted, it navigates to the deckScreen.
 */
fun deleteData(navController: NavController, collectionPath: String, id: String){
    val db = FirebaseFirestore.getInstance()

    db.collection(collectionPath)
        .document(id)
        .delete()
        .addOnFailureListener { e ->
            Log.w(ContentValues.TAG, "Error deleting documents.", e)
        }

    navController.navigate(route = BottomBarScreen.Deck.route)
}
