package com.example.sl3verbeterd

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class HireHubRepository(private val hireHubDao: HireHubDao, private val context: Context) {
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

    suspend fun deleteProfile(profile: Profile) {
        hireHubDao.deleteProfile(profile)
    }

    suspend fun upsertProfile(profile: Profile) {
        hireHubDao.upsertProfile(profile)

    }

    fun getProfilesOrderedByPhoneNumber(): Flow<List<Profile>> {
      return  hireHubDao.getProfilesOrderedByPhoneNumber()
    }

    fun getProfilesOrderedByFirstName(): Flow<List<Profile>> {
return hireHubDao.getProfilesOrderedByFirstName()
    }

   fun getProfilesOrderedByLastName(): Flow<List<Profile>> {
 return hireHubDao.getProfilesOrderedByLastName()
    }

    suspend fun updateProfile(firstName: String, id: Int) {
hireHubDao.updateProfile(firstName, id)
    }
}