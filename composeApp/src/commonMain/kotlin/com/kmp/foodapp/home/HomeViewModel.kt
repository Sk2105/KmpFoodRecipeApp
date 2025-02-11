package com.kmp.foodapp.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kmp.foodapp.data.holder.MealResponseHolder
import com.kmp.foodapp.data.network.Network
import com.kmp.foodapp.data.holder.ResponseStateHolder
import com.kmp.foodapp.data.state.MealState
import com.kmp.foodapp.data.state.ResponseState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class HomeViewModel() : ViewModel() {
    private val networkRepo = Network
    private val _meals = MutableStateFlow(ResponseStateHolder())
    val meals = _meals

    private val _meal = MutableStateFlow(MealResponseHolder())
    val meal = _meal

    init {
        fetchMeals()
    }

    private fun fetchMeals() =
        viewModelScope.launch {
            _meals.value = ResponseStateHolder()

            try {
                networkRepo.fetchMeals().collect {
                    _meals.value = ResponseStateHolder(state = ResponseState.Success(it))
                }

            } catch (e: Exception) {
                _meals.value =
                    ResponseStateHolder(state = ResponseState.Error(e.message.toString()))

            }
        }

    fun fetchMealById(id: String) = viewModelScope.launch {
        _meal.update { MealResponseHolder() }

        try {
            networkRepo.fetchMealsDetails(id).collect { res ->
                _meal.update {
                    MealResponseHolder(state = MealState.Success(res))
                }
            }
        } catch (e: Exception) {
            _meal.update {
                MealResponseHolder(state = MealState.Error(e.message.toString()))
            }
        }
    }

    fun reload() {
        fetchMeals()
    }
}