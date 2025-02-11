package com.kmp.foodapp.home.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.kmp.foodapp.data.model.Meal
import com.kmp.foodapp.data.state.ResponseState
import com.kmp.foodapp.home.HomeViewModel


@Composable
expect fun getCurrentWidth(): Int

@Composable
fun HomeScreen() {

    val viewModel: HomeViewModel = viewModel()


    val meals by viewModel.meals.collectAsStateWithLifecycle()

    var bottomNavigationState by remember {
        mutableStateOf(false)
    }
    var currentId by remember {
        mutableStateOf("")
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Food Recipe App",
                        color = Color.White
                    )
                },
                backgroundColor = Color.Blue
            )
        },
        modifier = Modifier.fillMaxSize()
    ) {

        when (meals.state) {
            is ResponseState.Error -> {
                val error = meals.state as ResponseState.Error
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = error.message,
                        color = Color.Red
                    )
                    Spacer(Modifier.padding(16.dp))

                    Button(onClick = {
                        viewModel.reload()

                    }){
                        Text(text = "Refresh")
                    }


                }
            }

            ResponseState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is ResponseState.Success -> {
                val success = meals.state as ResponseState.Success
                val data = success.data
                val width = getCurrentWidth()

                val cell = if (width < 500) {
                    2
                } else if (width < 700) {
                    3
                } else if (width < 900) {
                    4
                } else {
                    5
                }

                LazyVerticalGrid(
                    columns = GridCells.Fixed(cell),
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    data.meals.forEach {
                        item {
                            MealBox(meal = it) { id ->
                                currentId = id
                                bottomNavigationState = true

                            }
                        }

                    }
                }

            }
        }

        if (bottomNavigationState) {
            val meal = (meals.state as ResponseState.Success).data.meals.filter {
                it.idMeal == currentId
            }[0]

            val currentWidth = getCurrentWidth()
            val width = if (currentWidth < 500) {
                currentWidth / 2
            } else if (currentWidth < 700) {
                currentWidth / 3
            } else if (currentWidth < 900) {
                currentWidth / 4
            } else {
                currentWidth / 5

            }
            Dialog(onDismissRequest = {}, content = {

                Column(
                    modifier = Modifier.clip(RoundedCornerShape(16.dp)).fillMaxWidth()
                        .background(color = Color.White).padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    AsyncImage(
                        model = meal.strMealThumb,
                        contentDescription = meal.strMeal,
                        modifier = Modifier.padding(16.dp).clip(RoundedCornerShape(16.dp))
                            .size(width.dp),
                        contentScale = ContentScale.FillBounds
                    )

                    Text(
                        text = meal.strMeal,
                        modifier = Modifier.padding(4.dp),
                        color = Color.Black,
                        maxLines = 1,
                        fontSize = 24.sp


                    )

                    Button(onClick = {
                        bottomNavigationState = false

                    }) {
                        Text(
                            text = "Done",
                            fontSize = 20.sp
                        )
                    }

                }

            })
        }


    }

}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MealBox(meal: Meal, onClick: (String) -> Unit) {

    val currentWidth = getCurrentWidth()


    val width = if (currentWidth < 500) {
        currentWidth / 2
    } else if (currentWidth < 700) {
        currentWidth / 3
    } else if (currentWidth < 900) {
        currentWidth / 4
    } else {
        currentWidth / 5

    }



    Card(
        onClick = {
            onClick(meal.idMeal)

        },
        modifier = Modifier.padding(4.dp).clip(RoundedCornerShape(16.dp)).fillMaxWidth(),
        backgroundColor = Color.White,
        elevation = 8.dp,

        ) {
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                model = meal.strMealThumb,
                contentDescription = meal.strMeal,
                modifier = Modifier.clip(RoundedCornerShape(16.dp)).size(width.dp),
                contentScale = ContentScale.FillBounds
            )

            Text(
                text = meal.strMeal,
                modifier = Modifier.padding(4.dp),
                color = Color.Black,
                maxLines = 1,
                fontSize = 20.sp


            )


        }

    }
}