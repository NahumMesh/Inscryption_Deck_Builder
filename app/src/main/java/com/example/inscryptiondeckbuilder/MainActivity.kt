package com.example.inscryptiondeckbuilder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.inscryptiondeckbuilder.ui.theme.InscryptionDeckBuilderTheme
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class MainActivity : ComponentActivity() {
    private val cardsCollection = Firebase.firestore.collection("cards")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InscryptionDeckBuilderTheme {
                val navController = rememberNavController()
                Nav(navController)
            }
        }
    }
}

