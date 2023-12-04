package com.example.inscryptiondeckbuilder.data

data class CardData(
    var card_name: String? = null,
    var power: Int? = null,
    var health: Int? = null,
    var cost: String? = null,
    var card_image_file_name: String? = null,
    var sigils: List<SigilData> = emptyList()
)