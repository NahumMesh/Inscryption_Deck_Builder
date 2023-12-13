package com.example.inscryptiondeckbuilder.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage


/**
 * The DetailCardScreen Composable function splits the sigil data up.
 * Then displays all of the data inside rows and an async image.
 * The FAB will call the writeData function to add the card to the deck.
 */
@SuppressLint("UnrememberedMutableState")
@Composable
fun DetailCardScreen(
    modifier: Modifier = Modifier,
    photos: String?,
    names: String?,
    cost: String?,
    health: Int?,
    power: Int?,
    sigils: List<HashMap<String, String>>?
) {
    // This is splitting up the sigil data by name and effect.
    val sigilOneName: String? = sigils?.get(0)?.get("name")
    val sigilOneEffect: String? = sigils?.get(0)?.get("effect")

    val sigilTwoName: String? = sigils?.get(1)?.get("name")
    val sigilTwoEffect: String? = sigils?.get(1)?.get("effect")

    Column(
        modifier
            .fillMaxSize()
            .padding(25.dp),
        verticalArrangement = Arrangement.spacedBy(3.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = photos,
            contentDescription = "Inscryption Card Image",
            modifier
                .size(width = 200.dp, height = 300.dp)
                .padding(top = 60.dp),
            filterQuality = FilterQuality.None
        )

        Text(
            text = names.toString(),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Row(
            modifier.wrapContentSize(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = "Cost: " + cost.toString(),
                fontSize = 18.sp
            )
        }

        Row(
            modifier.wrapContentSize(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = "Health: " + health.toString(),
                modifier.padding(horizontal = 10.dp),
                fontSize = 18.sp
            )
            Text(text = "Power: " + power.toString(),
                modifier.padding(horizontal = 10.dp),
                fontSize = 18.sp
            )
        }

        Row(
            modifier.wrapContentSize(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            if (sigilOneName != "NULL") {
                Text(
                    text = sigilOneName.toString() + ": " + sigilOneEffect.toString(),
                    fontSize = 18.sp
                )
            }
        }

        Row(
            modifier.wrapContentSize(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            if (sigilTwoName != "NULL"){
                Text(text = sigilTwoName.toString() + ": " + sigilTwoEffect.toString(),
                    fontSize = 18.sp
                )
            }
        }

        // This FAB will call the writeData function, which adds the card from the deck and calls a toast.
        val context = LocalContext.current
        ExtendedFloatingActionButton(
            text = { Text(text = "Add card to deck") },
            icon = { Icon(Icons.Filled.Add, contentDescription = "Adding card to deck") },
            onClick = {
                writeData(photos = photos, names = names, cost = cost, health = health, power = power, sigils = sigils)
                Toast.makeText(context, "Card Added to Deck!", Toast.LENGTH_SHORT).show()
            },
            modifier.padding(top = 50.dp),
            containerColor = Color(0xFFAD9CA6)
        )
    }
}

