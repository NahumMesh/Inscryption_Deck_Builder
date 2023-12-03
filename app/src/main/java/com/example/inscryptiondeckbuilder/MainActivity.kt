package com.example.inscryptiondeckbuilder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.inscryptiondeckbuilder.ui.theme.InscryptionDeckBuilderTheme
import com.example.inscryptiondeckbuilder.navgraphs.RootNavGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InscryptionDeckBuilderTheme {
                RootNavGraph(navController = rememberNavController())
            }
        }
    }
}

