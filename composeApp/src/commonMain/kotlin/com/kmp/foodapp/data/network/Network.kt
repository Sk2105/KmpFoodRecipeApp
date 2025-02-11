package com.kmp.foodapp.data.network

import com.kmp.foodapp.data.model.MealDetails
import com.kmp.foodapp.data.model.MealResponse
import com.kmp.foodapp.utils.BASE_URL
import com.kmp.foodapp.utils.PATH
import com.kmp.foodapp.utils.PATH2
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.flow

object Network {

    val httpClient = getHttpClient()

    suspend fun fetchMeals() = flow {
        try {
            val meals = httpClient.get(BASE_URL + PATH).body<MealResponse>()
            emit(meals)
        } catch (e: Exception) {
            throw e
        }
    }

    suspend fun fetchMealsDetails(id: String) = flow {
        try {
            val meals = httpClient.get(BASE_URL + PATH2 + id).body<MealDetails>()
            emit(meals)

        } catch (e: Exception) {
            throw e
        }
    }
}