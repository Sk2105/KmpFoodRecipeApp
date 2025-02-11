package com.kmp.foodapp.data.holder

import com.kmp.foodapp.data.state.MealState
import com.kmp.foodapp.data.state.ResponseState

data class ResponseStateHolder(
    val state : ResponseState = ResponseState.Loading
)

data class MealResponseHolder(
    val state: MealState = MealState.Loading
)