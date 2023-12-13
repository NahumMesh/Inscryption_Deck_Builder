package com.example.inscryptiondeckbuilder

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * BottomBarScreen sealed class that simplifies the calling of the routes, titles, and icons of all
 * the different bottom bar buttons.
 */
sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : BottomBarScreen(
        route = "HOME",
        title = "HOME",
        icon = Icons.Default.Home
    )

    object Deck : BottomBarScreen(
        route = "DECK",
        title = "DECK",
        icon = Icons.Default.Build
    )

    object Settings : BottomBarScreen(
        route = "SETTINGS",
        title = "SETTINGS",
        icon = Icons.Default.Settings
    )
}
