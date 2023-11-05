package com.example.sl3verbeterd

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Profile(
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)
