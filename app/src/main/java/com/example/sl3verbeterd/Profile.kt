package com.example.sl3verbeterd


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "profiles")
data class Profile(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val accountId: Int,
    val name: String,
    val email: String
)