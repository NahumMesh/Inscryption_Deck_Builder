package com.example.inscryptiondeckbuilder.navgraphs

import android.content.ContentValues
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.inscryptiondeckbuilder.BottomBarScreen
import com.example.inscryptiondeckbuilder.screens.Card
import com.example.inscryptiondeckbuilder.screens.CardCatalog
import com.example.inscryptiondeckbuilder.screens.CardId
import com.example.inscryptiondeckbuilder.screens.ScreenContent
import com.example.inscryptiondeckbuilder.screens.DetailScreen
import com.google.common.collect.Maps
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import org.checkerframework.checker.units.qual.K

@Composable
fun HomeNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            CardCatalog(navController)
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

        cardNavGraph(navController = navController, id = CardId)
    }
}

fun NavGraphBuilder.cardNavGraph(navController: NavHostController, id: CardId) {
    val db = Firebase.firestore
    var cardDataList: MutableList<Card> = mutableListOf()

    val currentRoute = navController.currentBackStackEntry?.destination

    var cardImage: String? = ""
    var cardName: String? = ""
    var cardCost: String? = ""
    var cardHealth: Int? = 0
    var cardPower: Int? = 0
    var cardSigils: List<HashMap<String, String>> = emptyList()
    Log.d(ContentValues.TAG, "cardNavGraph:${currentRoute} ")

    db.collection("cards").document("$id")
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