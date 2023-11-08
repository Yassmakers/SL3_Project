package com.example.sl3verbeterd

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow

class HireHubRepository(private val hireHubDao: HireHubDao) {

    val allProfiles: Flow<List<Profile>> = hireHubDao.getProfilesOrderedByFirstName()
    suspend fun showProfile(id: Int): LiveData<Profile> {
        return hireHubDao.showProfile(id)
    }

    suspend fun insertProfile(profile: Profile) {
        hireHubDao.insertProfile(profile)
    }

    suspend fun deleteProfile(profile: Profile) {
        hireHubDao.deleteProfile(profile)
    }

    suspend fun deleteAccount(account: Account) {
        hireHubDao.deleteAccount(account)
    }

    suspend fun deleteAccountToo(id: Int) {
        hireHubDao.deleteAccountToo(id)
    }

    suspend fun updateProfile(profile: Profile) {
        hireHubDao.updateProfile(profile)
    }

    suspend fun getUserByUsername(username: String): Account? {
        return hireHubDao.getUserByUsername(username)
    }

    suspend fun insertAccount(account: Account) {
        hireHubDao.insertAccount(account)
    }

    suspend fun updateAccount(account: Account) {
        hireHubDao.updateAccount(account)
    }
}
