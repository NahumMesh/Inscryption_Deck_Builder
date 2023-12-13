package com.example.inscryptiondeckbuilder.screens

import android.annotation.SuppressLint
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
import coil.compose.AsyncImage
import com.example.inscryptiondeckbuilder.data.CardData
import com.example.inscryptiondeckbuilder.data.CardImage
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

/**
 * The CardScreen Composable function displays the LazyVerticalGrid with the data from the FireStore.
 * Once the card is clicked, it navigates to the DetailCardScreen.
 */
@SuppressLint("UnrememberedMutableState")
@Composable
fun CardScreen(onNavigateToCardScreen: (String) -> Unit) {
    val db = Firebase.firestore
    val cardDataList = mutableStateListOf<CardImage>()

    db.collection("cards")
        .get()
        .addOnSuccessListener { cards ->
            for (card in cards) {
                val image = card.data["card_image_file_name"].toString()
                val id = card.id
                val name = card.data["card_name"].toString()
                cardDataList.add(CardImage(id, image, name))
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
        items(cardDataList) { data ->

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
 * The ScrapeCard Composable function scrapes data from the FireStore.
 * It then calls the DetailCardScreen and passes the values into it.
 */
@Composable
fun ScrapeCard(cardId: String) {
    val db = Firebase.firestore
    val cardData by remember { mutableStateOf(mutableStateListOf<CardData>()) }

    db.collection("cards").document(cardId)
        .get()
        .addOnSuccessListener { items ->
            val id = items.id
            val image = items.data?.get("card_image_file_name").toString()
            val names = items.data?.get("card_name").toString()
            val cost = items.data?.get("cost").toString()
            val health = items.data?.get("health") as? Long
            val power = items.data?.get("power") as? Long
            val sigils = items.data?.get("sigils") as? List<HashMap<String, String>>

            cardData.add(CardData(id, image, names, cost, health, power, sigils))
        }
        .addOnFailureListener { exception ->
            Log.w(ContentValues.TAG, "Error getting documents.", exception)
        }

    // Adding the queried data into the DetailCardScreen.
    for (data in cardData) {
        DetailCardScreen(
            photos = data.card_image_file_name,
            names = data.card_name,
            cost = data.cost,
            health = data.health?.toInt(),
            power = data.power?.toInt(),
            sigils = data.sigils
        )
    }
}