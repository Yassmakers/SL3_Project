package com.example.sl3verbeterd

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Update
import androidx.room.Delete
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
@Dao
interface AccountDao {
    @Insert
    fun insert(account: Account): Long
    @Query("SELECT * FROM accounts")
    fun getAllAccounts(): List<Account>

    @Update
    suspend fun update(account: Account)



    @Delete
    suspend fun delete(account: Account)
}