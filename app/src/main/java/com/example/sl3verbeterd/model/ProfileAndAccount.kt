package com.example.sl3verbeterd.model

import com.example.sl3verbeterd.Account
import com.example.sl3verbeterd.Profile

data class ProfileAndAccount(
    val firstName: String,
    val lastName: String,
    val location: String,
    val job: String,
    val username: String,
    val password: String,
    val role: String,
    val id: Int
)
