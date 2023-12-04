package com.example.inscryptiondeckbuilder

data class CardData(
    val card_name: String = "",
    val power: Int = 0,
    val health: Int = 0,
    val cost: String = "",
    val card_image_file_name: String = "",
    val sigils: List<SigilData> = emptyList()
)