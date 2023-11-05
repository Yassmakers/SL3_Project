package com.example.sl3verbeterd

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Profile::class],
    version = 1
)
abstract class ProfileDatabase: RoomDatabase() {

    abstract val dao: ProfileDao
}