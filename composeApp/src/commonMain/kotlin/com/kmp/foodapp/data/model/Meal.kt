@file:Suppress("PLUGIN_IS_NOT_ENABLED")

package com.kmp.foodapp.data.model

import kotlinx.serialization.Serializable


@Serializable
data class Meal(
    val idMeal: String,
    val strMeal: String,
    val strMealThumb: String
)