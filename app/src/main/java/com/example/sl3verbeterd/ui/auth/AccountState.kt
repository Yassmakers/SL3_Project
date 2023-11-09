package com.example.sl3verbeterd.ui.auth

data class AccountState(
    val isAuthenticated: Boolean = false,
    val role: String = "guest",
    val id: Int = 0
)


