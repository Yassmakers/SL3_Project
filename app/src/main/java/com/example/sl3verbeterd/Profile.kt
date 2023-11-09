package com.example.sl3verbeterd

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "profile")
data class Profile(
    val firstName: String,
    val lastName: String,
    val location: String,
    val job: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
) : Serializable
