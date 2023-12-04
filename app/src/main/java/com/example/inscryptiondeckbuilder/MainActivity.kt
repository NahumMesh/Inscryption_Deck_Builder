package com.example.inscryptiondeckbuilder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.inscryptiondeckbuilder.ui.theme.InscryptionDeckBuilderTheme
import com.example.inscryptiondeckbuilder.navgraphs.RootNavGraph
import com.google.firebase.Firebase
import com.google.firebase.appcheck.appCheck
import com.google.firebase.appcheck.playintegrity.PlayIntegrityAppCheckProviderFactory
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.initialize

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Firebase.initialize(context = this)

        Firebase.appCheck.installAppCheckProviderFactory(
            PlayIntegrityAppCheckProviderFactory.getInstance()
        )

        val auth = FirebaseAuth.getInstance()
        auth.signInAnonymously()

        setContent {
            InscryptionDeckBuilderTheme {
                RootNavGraph(navController = rememberNavController())
            }
        }
    }
}

