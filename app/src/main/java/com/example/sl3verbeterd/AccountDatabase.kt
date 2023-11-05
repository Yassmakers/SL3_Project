package com.example.sl3verbeterd

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Account::class],
    version = 1
)
abstract class AccountDatabase: RoomDatabase() {

    abstract val dao: AccountDao
}