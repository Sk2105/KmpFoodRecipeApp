package com.kmp.foodapp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MealDetails(
    @SerialName("meals")
    val meals: List<Meals>
)