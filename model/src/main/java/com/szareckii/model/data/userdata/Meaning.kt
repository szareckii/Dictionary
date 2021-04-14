package com.szareckii.model.data.userdata

data class Meaning(
    val translatedMeaning: TranslatedMeaning = TranslatedMeaning(),
    val imageUrl: String = "",
    val transcription: String = ""
)
