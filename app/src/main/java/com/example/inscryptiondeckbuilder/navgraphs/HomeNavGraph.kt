package com.example.inscryptiondeckbuilder.navgraphs

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import coil.compose.AsyncImage
import com.example.inscryptiondeckbuilder.BottomBarScreen
import com.example.inscryptiondeckbuilder.screens.Card
import com.example.inscryptiondeckbuilder.screens.ScreenContent
import com.example.inscryptiondeckbuilder.screens.DetailScreen
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import androidx.compose.runtime.*
import androidx.navigation.compose.*


@Composable
fun HomeNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            CardCatalog(onNavigateToCardScreen = {
                navController.navigate(route = "card_data/$it")
            })
        }

        composable(route = "card_data/{id}",
            arguments = listOf(
                navArgument(name = "id"){
                    type = NavType.StringType
                }
            )
        ) {
            val cardId = it.arguments!!.getString("id").toString()
            scrapeData(cardId = cardId)
        }

        composable(route = BottomBarScreen.Deck.route) {
            ScreenContent(
                name = BottomBarScreen.Deck.route,
                onClick = { }
            )
        }

        composable(route = BottomBarScreen.Settings.route) {
            ScreenContent(
                name = BottomBarScreen.Settings.route,
                onClick = { }
            )
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun CardCatalog(onNavigateToCardScreen: (String) -> Unit) {
    val db = Firebase.firestore
    val cardDataList by remember { mutableStateOf(mutableStateListOf<Card>()) }

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
            Log.w(ContentValues.TAG, "Error getting documents.", exception)
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
                onNavigateToCardScreen = onNavigateToCardScreen
            )
        }
    }
}

@Composable
fun ColumnItem(
    imageUrl: String,
    itemIndex: String,
    onNavigateToCardScreen: (String) -> Unit
){
    Row(
        modifier = Modifier
            .background(color = Color.DarkGray)
            .fillMaxWidth()
            .padding((5.dp))
            .clickable {
                onNavigateToCardScreen(itemIndex)
            }
    ) {
        AsyncImage(
            model = imageUrl,
            filterQuality = FilterQuality.None,
            contentDescription = "Inscryption Card Image"
        )
    }
}

@Composable
fun scrapeData(cardId: String) {
    val db = Firebase.firestore
    val cardData by remember { mutableStateOf(mutableStateListOf<Cards>()) }

    db.collection("cards").document(cardId)
        .get()
        .addOnSuccessListener { items ->
            val image = items.data?.get("card_image_file_name").toString()
            val names = items.data?.get("card_name").toString()
            val cost = items.data?.get("cost").toString()
            val health = items.data?.get("health") as Long
            val power = items.data?.get("power") as Long
            val sigils = items.data?.get("sigils") as List<HashMap<String, String>>

            cardData.add(Cards(image, names, cost, health, power, sigils))
        }
        .addOnFailureListener { exception ->
            Log.w(ContentValues.TAG, "Error getting documents.", exception)
        }

    for (data in cardData) {

        Log.d(ContentValues.TAG, "Card Name?${data.card_name}")
        DetailScreen(
            photos = data.card_image_file_name,
            names = data.card_name,
            cost = data.cost,
            health = data.health?.toInt(),
            power = data.power?.toInt(),
            sigils = data.sigils
        )
    }
}

data class Cards(
    val card_image_file_name: String? = "",
    val card_name: String? = "",
    val cost: String? = "",
    val health: Long? = 0,
    val power: Long? = 0,
    val sigils: List<HashMap<String, String>> = emptyList()
)
