package com.example.sl3verbeterd

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "account")
data class Account(
    val username: String,
    val password: String,
    val role: String, // "guest", "user", "recruiter" & "admin"
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)
