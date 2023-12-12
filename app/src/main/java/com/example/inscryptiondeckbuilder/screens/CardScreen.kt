package com.example.inscryptiondeckbuilder.screens

import android.annotation.SuppressLint
import android.content.ContentValues
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@SuppressLint("UnrememberedMutableState")
@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    photos: String?,
    names: String?,
    cost: String?,
    health: Int?,
    power: Int?,
    sigils: List<HashMap<String, String>>?
) {
    val sigilOneName: String? = sigils!![0]["name"]
    val sigilOneEffect: String? = sigils[0]["effect"]

    val sigilTwoName: String? = sigils[1]["name"]
    val sigilTwoEffect: String? = sigils[1]["effect"]

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
            modifier.size(width = 200.dp, height = 300.dp).padding(top = 60.dp),
            filterQuality = FilterQuality.None
        )
        Text(text = names.toString(), fontSize = 30.sp, fontWeight = FontWeight.Bold)

        Row(
            modifier.wrapContentSize(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            Text(text = "Cost: " + cost.toString(),fontSize = 18.sp)

        }
        Row(
            modifier.wrapContentSize(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = "Health: " + health.toString(), modifier.padding(horizontal = 10.dp), fontSize = 18.sp)
            Text(text = "Power: " + power.toString(), modifier.padding(horizontal = 10.dp), fontSize = 18.sp)
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
                Text(text = sigilTwoName.toString() + ": " + sigilTwoEffect.toString(), fontSize = 18.sp)
            }
        }
        ExtendedFloatingActionButton(
            text = { Text(text = "Add card to deck") },
            icon = { Icon(Icons.Filled.Add, "Adding card to deck") },
            onClick = {
                      /* do something */
                      },
            modifier.padding(top = 50.dp),
            containerColor = Color(0xFFAD9CA6)
        )
    }
}
data class Card(
    val id: String = "",
    val card_image_file_name: String = "",
    val card_name: String = "",
    val cost: String = "",
    val health: Any? = 0,
    val power: Any? = 0
)
