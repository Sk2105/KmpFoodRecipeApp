package com.kmp.foodapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class MealResponse(
    val meals: List<Meal>
)