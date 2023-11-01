package com.example.sl3verbeterd

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class HireHubRepository(private val database: HireHubRoomDatabase) {

    suspend fun insertAccount(account: Account) {
        database.accountDao().insert(account)
    }
    suspend fun updateAccount(account: Account) {
        database.accountDao().update(account)
    }


    suspend fun deleteAccount(account: Account) {
        database.accountDao().delete(account)
    }

    suspend fun insertProfile(profile: Profile) {
        database.profileDao().insert(profile)
    }

    suspend fun updateProfile(profile: Profile) {
        database.profileDao().update(profile)
    }

    suspend fun deleteProfile(profile: Profile) {
        database.profileDao().delete(profile)
    }

    fun getAllProfiles(): List<Profile>? {
        TODO("Not yet implemented")
    }

    fun getAllAccounts(): List<Account>? {
        TODO("Not yet implemented")
    }


}