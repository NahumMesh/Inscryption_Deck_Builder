package com.example.inscryptiondeckbuilder.data

/**
 * CardData data class used as a template for holding information grabbed from FireStore. This
 * information displays the cards image and all its details on the DetailCardScreen and DetailDeckScreen.
 */
data class CardData(
    val id: String = "",
    val card_image_file_name: String? = "",
    val card_name: String? = "",
    val cost: String? = "",
    val health: Long? = 0,
    val power: Long? = 0,
    val sigils: List<HashMap<String, String>>? = emptyList()
)

/**
 * CardImage data class used as a template for holding information grabbed from FireStore. This
 * information displays the cards image and name on the CardScreen and DeckScreen.
 */
data class CardImage(
    val id: String = "",
    val card_image_file_name: String = "",
    val card_name: String = ""
)
