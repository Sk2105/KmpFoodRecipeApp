package com.kmp.foodapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform