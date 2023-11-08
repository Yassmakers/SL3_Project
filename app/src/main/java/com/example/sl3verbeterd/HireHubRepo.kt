package com.example.sl3verbeterd

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow

class HireHubRepository(private val hireHubDao: HireHubDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allProfiles: Flow<List<Profile>> = hireHubDao.getProfilesOrderedByFirstName()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @WorkerThread
    suspend fun insertProfile(profile: Profile) {
        hireHubDao.insertProfile(profile)
    }
//
    suspend fun deleteProfile(profile: Profile) {
        hireHubDao.deleteProfile(profile)
    }

    suspend fun updateProfile(profile: Profile) {
        hireHubDao.updateProfile(profile)
    }

//    fun showProfile(id: Int) {
//        hireHubDao.showProfile(id)
//    }

    fun showProfile (id: Int):LiveData<Profile> {
        return hireHubDao.showProfile(id)
    }

    suspend fun insertAccount(account: Account) {
        hireHubDao.insertAccount(account)
    }

    suspend fun updateAccount(account: Account) {
        hireHubDao.updateAccount(account)
    }

//
//    suspend fun upsertProfile(profile: Profile) {
//        hireHubDao.upsertProfile(profile)
//
//    }
//
//    fun getProfilesOrderedByPhoneNumber(): Flow<List<Profile>> {
//      return  hireHubDao.getProfilesOrderedByPhoneNumber()
//    }
//
//    fun getProfilesOrderedByFirstName(): Flow<List<Profile>> {
//return hireHubDao.getProfilesOrderedByFirstName()
//    }
//
//   fun getProfilesOrderedByLastName(): Flow<List<Profile>> {
// return hireHubDao.getProfilesOrderedByLastName()
//    }
//
//    suspend fun updateProfile(firstName: String, id: Int) {
//hireHubDao.updateProfile(firstName, id)
//    }


}