package com.albert.infinitespirit.features.drink.domain

data class Drink(
    val id: String? = null,
    val name: String? = null,
    val description: String? = null,
    val origin: String? = null,
    val photo: String? = null,
    val timeRegister: String? = null,
    val timeUpdate: String? = null,
    val ingredients: List<String>? = listOf(),
    val preparationSteps: List<String>? = listOf()
)