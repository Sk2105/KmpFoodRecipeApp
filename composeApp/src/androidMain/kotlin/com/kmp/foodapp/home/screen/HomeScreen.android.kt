package com.kmp.foodapp.home.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration


@Composable
actual fun getCurrentWidth(): Int {
    val config = LocalConfiguration.current.screenWidthDp
    return config
}