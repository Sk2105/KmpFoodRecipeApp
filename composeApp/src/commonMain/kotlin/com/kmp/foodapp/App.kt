package com.kmp.foodapp

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import com.kmp.foodapp.home.screen.HomeScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
       HomeScreen()
    }
}