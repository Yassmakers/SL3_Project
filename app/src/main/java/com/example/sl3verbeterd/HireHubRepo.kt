package com.example.sl3verbeterd


import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import com.example.sl3verbeterd.model.ProfileAndAccount


class HireHubRepository(private val hireHubDao: HireHubDao) {

    val allProfiles: Flow<List<Profile>> = hireHubDao.getProfilesOrderedByFirstName()
    val allVisibleProfiles: Flow<List<Profile>> = hireHubDao.getAllVisibleProfiles()

    suspend fun showProfile(id: Int): LiveData<Profile> {
        return hireHubDao.showProfile(id)
    }

    suspend fun insertProfile(profile: Profile) {
        hireHubDao.insertProfile(profile)
    }

    suspend fun resetProfile(id: Int) {
        hireHubDao.resetProfile(id)
    }

    suspend fun toggleVisibility(id: Int) {
        hireHubDao.toggleVisibility(id)
    }

    fun getProfileAndAccountById(id: Int): LiveData<ProfileAndAccount> {
        return hireHubDao.getProfileAndAccountById(id)
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

    // Public method to get profile by ID
    fun getProfileById(profileId: Int): LiveData<Profile> {
        return hireHubDao.getProfileById(profileId)
    }
    suspend fun updateAccount(account: Account) {
        hireHubDao.updateAccount(account)
    }
}
