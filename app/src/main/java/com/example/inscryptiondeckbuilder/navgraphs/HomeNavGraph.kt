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
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import coil.compose.AsyncImage
import com.example.inscryptiondeckbuilder.BottomBarScreen
import com.example.inscryptiondeckbuilder.screens.Card
import com.example.inscryptiondeckbuilder.screens.ScreenContent
import com.example.inscryptiondeckbuilder.screens.DetailScreen
import com.google.common.collect.Maps
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import org.checkerframework.checker.units.qual.K

@Composable
fun HomeNavGraph(navController: NavHostController) {
    val db = Firebase.firestore

    var cardImage: String? = ""
    var cardName: String? = ""
    var cardCost: String? = ""
    var cardHealth: Int? = 0
    var cardPower: Int? = 0
    var cardSigils: List<HashMap<String, String>> = emptyList()

    db.collection("cards").document("0FiarXa3hXjg2gcLohGg")
        .get()
        .addOnSuccessListener { card ->
            cardImage = card.getString("card_image_file_name")
            cardName = card.getString("card_name")
            cardCost = card.getString("cost")
            cardHealth = card.getLong("health")?.toInt()
            cardPower = card.getLong("power")?.toInt()
            cardSigils = card.get("sigils") as List<HashMap<String, String>>

            Log.d(ContentValues.TAG, "Error getting documents.")


        }
        .addOnFailureListener { exception ->
            Log.w(ContentValues.TAG, "Error getting documents.", exception)
        }



    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            CardCatalog(navController)
        }

        composable(route = "card_data/0FiarXa3hXjg2gcLohGg",
            arguments = listOf(
                navArgument(name = "id"){
                    type = NavType.StringType
                }
            )
        ) {id ->
            DetailScreen(
                photos = cardImage,
                names = cardName,
                cost = cardCost,
                health = cardHealth,
                power = cardPower,
                sigils = cardSigils,
                id = id.arguments?.getString("id")
            )
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

        //cardNavGraph(navController = navController)
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun CardCatalog(navController: NavHostController) {
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


fun NavGraphBuilder.cardNavGraph(navController: NavHostController) {
    val db = Firebase.firestore

    var cardImage: String? = ""
    var cardName: String? = ""
    var cardCost: String? = ""
    var cardHealth: Int? = 0
    var cardPower: Int? = 0
    var cardSigils: List<HashMap<String, String>> = emptyList()
    
    db.collection("cards").document("0FiarXa3hXjg2gcLohGg")
        .get()
        .addOnSuccessListener { card ->
            cardImage = card.getString("card_image_file_name")
            cardName = card.getString("card_name")
            cardCost = card.getString("cost")
            cardHealth = card.getLong("health")?.toInt()
            cardPower = card.getLong("power")?.toInt()
            cardSigils = card.get("sigils") as List<HashMap<String, String>>

            Log.d(ContentValues.TAG, "Error getting documents.")


        }
        .addOnFailureListener { exception ->
            Log.w(ContentValues.TAG, "Error getting documents.", exception)
        }

    navigation(
        route = "card_data/0FiarXa3hXjg2gcLohGg",
        startDestination = Graph.card_data,
    ) {
        composable(route = Graph.card_data,
            arguments = listOf(
                navArgument(name = "id"){
                    type = NavType.StringType
                }
            )
        ) {id ->
            DetailScreen(
                photos = cardImage,
                names = cardName,
                cost = cardCost,
                health = cardHealth,
                power = cardPower,
                sigils = cardSigils,
                id = id.arguments?.getString("id")
            )
            Log.d(ContentValues.TAG, "Route Card ID:${id}")
        }
    }
}

sealed class CardScreen(val route: String) {
    object CardInfo : CardScreen(route = "CARD INFO")
}