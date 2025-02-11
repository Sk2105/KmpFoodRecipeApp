package com.kmp.foodapp.data.state

import com.kmp.foodapp.data.model.MealDetails
import com.kmp.foodapp.data.model.MealResponse


sealed interface ResponseState {
    data object Loading : ResponseState
    data class Success(val data: MealResponse) : ResponseState
    data class Error(val message: String) : ResponseState
}

sealed interface MealState{
    data object Loading : MealState
    data class Success(val data: MealDetails): MealState
    data class Error(val message:String): MealState
}