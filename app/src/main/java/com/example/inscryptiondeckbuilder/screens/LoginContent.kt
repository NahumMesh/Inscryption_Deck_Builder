package com.example.inscryptiondeckbuilder.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

@Composable
fun LoginContent(onClick: () -> Unit, onSignUpClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.clickable { onClick() },
            text = "LOGIN",
            fontSize = MaterialTheme.typography.headlineLarge.fontSize,
            fontWeight = FontWeight.Bold
        )

        Text(
            modifier = Modifier.clickable { onSignUpClick() },
            text = "Sign Up",
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            fontWeight = FontWeight.Medium,
            color = Color.Blue
        )
    }
}