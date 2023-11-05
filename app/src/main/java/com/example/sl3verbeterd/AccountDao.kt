package com.example.sl3verbeterd

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDao {

    // Combination of Insert and Update
    @Upsert
    suspend fun upsertAccount(account: Account)

    @Delete
    suspend fun deleteAccount(account: Account)


    //Custom Queries

    // Flow is a data structure that observes any changes in the list.
    // And updates the list.
    @Query("SELECT * FROM Account ORDER BY firstName ASC")
    fun getAccountsOrderedByFirstName(): Flow<List<Account>>

    @Query("SELECT * FROM Account ORDER BY lastName ASC")
    fun getAccountsOrderedByLastName(): Flow<List<Account>>

    @Query("SELECT * FROM Account ORDER BY phoneNumber ASC")
    fun getAccountsOrderedByPhoneNumber(): Flow<List<Account>>


    @Query("UPDATE Account SET firstName=:firstName WHERE id LIKE :id")
    suspend fun updateAccount(firstName: String, id: Int)
}