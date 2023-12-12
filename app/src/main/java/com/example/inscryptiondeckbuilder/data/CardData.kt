package com.example.inscryptiondeckbuilder.data

data class CardData(
    val id: String = "",
    val card_image_file_name: String? = "",
    val card_name: String? = "",
    val cost: String? = "",
    val health: Long? = 0,
    val power: Long? = 0,
    val sigils: List<HashMap<String, String>>? = emptyList()
)

data class CardImage(
    val id: String = "",
    val card_image_file_name: String = "",
    val card_name: String = ""
)
