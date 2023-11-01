package com.example.sl3verbeterd

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Update
import androidx.room.Delete
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ProfileDao {
    @Insert
    fun insert(profile: Profile): Long
    @Query("SELECT * FROM profiles WHERE accountId = :accountId")
    fun getProfilesForAccount(accountId: Int): List<Profile>

    @Update
    suspend fun update(profile: Profile)

    @Delete
    suspend fun delete(profile: Profile)
}