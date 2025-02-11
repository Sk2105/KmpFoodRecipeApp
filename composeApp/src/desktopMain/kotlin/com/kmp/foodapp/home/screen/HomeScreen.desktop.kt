package com.kmp.foodapp.home.screen

import androidx.compose.runtime.Composable
import java.awt.Toolkit

@Composable
actual fun getCurrentWidth(): Int {
    return Toolkit.getDefaultToolkit().screenSize.width
}