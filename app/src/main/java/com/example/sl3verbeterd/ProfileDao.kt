package com.example.sl3verbeterd

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ProfileDao {

    // Combination of Insert and Update
    @Upsert
    suspend fun upsertProfile(profile: Profile)

    @Delete
    suspend fun deleteProfile(profile: Profile)


    //Custom Queries

    // Flow is a data structure that observes any changes in the list.
    // And updates the list.
    @Query("SELECT * FROM Profile ORDER BY firstName ASC")
    fun getProfilesOrderedByFirstName(): Flow<List<Profile>>

    @Query("SELECT * FROM Profile ORDER BY lastName ASC")
    fun getProfilesOrderedByLastName(): Flow<List<Profile>>

    @Query("SELECT * FROM Profile ORDER BY phoneNumber ASC")
    fun getProfilesOrderedByPhoneNumber(): Flow<List<Profile>>


    @Query("UPDATE Profile SET firstName=:firstName WHERE id LIKE :id")
    suspend fun updateProfile(firstName: String, id: Int)
}