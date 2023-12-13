package com.example.inscryptiondeckbuilder
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.inscryptiondeckbuilder.ui.theme.InscryptionDeckBuilderTheme
import com.example.inscryptiondeckbuilder.navgraphs.RootNavGraph
import com.example.inscryptiondeckbuilder.screens.SettingsScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val darkTheme = remember { mutableStateOf(false)}
            InscryptionDeckBuilderTheme(darkTheme = darkTheme.value) {
                RootNavGraph(navController = rememberNavController(), darkTheme = darkTheme)
            }
        }
    }
}

