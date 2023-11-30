package com.example.inscryptiondeckbuilder

data class CardData(
    val cardName:String = "",
    val power:Int = 0,
    val health:Int = 0,
    val cost:String = "",
    val cardImageFileName:String = "",
    val sigils:List<SigilData> = emptyList()
)