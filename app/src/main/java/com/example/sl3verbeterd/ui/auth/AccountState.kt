package com.example.sl3verbeterd.ui.auth

data class AccountState(
val isAuthenticated: Boolean = false,
val id: String = "",
val username: String = "",
val rol: String = "guest",
val firstName: String = "",
val lastName: String = ""
)

