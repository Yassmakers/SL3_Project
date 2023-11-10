package com.example.sl3verbeterd
import com.example.sl3verbeterd.HireHubRepository
import com.example.sl3verbeterd.ui.applicant.ApplicantsViewModelFactory
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sl3verbeterd.model.ProfileAndAccount
import kotlinx.coroutines.flow.Flow

@Dao
interface HireHubDao {



    ////////////////  ////////////////  ////////////////
    ////////////////  /// Profile ////  ////////////////
    ////////////////  ////////////////  ////////////////

    //    @Query("UPDATE profile SET visibility = NOT visibility WHERE id = :id")
//    suspend fun toggleVisibility(id: Int)

    @Upsert
    suspend fun upsertProfile(profile: Profile)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProfile(profile: Profile)

    @Delete
    suspend fun deleteProfile(profile: Profile)

    @Query("DELETE FROM profile")
    suspend fun deleteAll()

    @Query("SELECT * FROM profile WHERE id IS :id")
    fun showProfile(id: Int) : LiveData<Profile>

    @Update
    suspend fun updateProfile(profile: Profile)

    @Query("SELECT * FROM profile ORDER BY firstName ASC")
    fun getProfilesOrderedByFirstName(): Flow<List<Profile>>

    @Query("SELECT * FROM profile ORDER BY lastName ASC")
    fun getProfilesOrderedByLastName(): Flow<List<Profile>>

    @Query("SELECT * FROM profile WHERE id = :profileId")
    fun getProfileById(profileId: Int): LiveData<Profile>

    @Query("UPDATE profile SET firstName='Voornaam', lastName='Achternaam', location='Woonplaats', job='Je functie' WHERE id = :id")
    suspend fun resetProfile(id: Int)


    ////////////////  ////////////////  ////////////////
    ////////////////  /// Account ////  ////////////////
    ////////////////  ////////////////  ////////////////

    @Upsert
    suspend fun upsertAccount(account: Account)

    @Delete
    suspend fun deleteAccount(account: Account)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAccount(account: Account)

    @Update
    suspend fun updateAccount(account: Account)

    @Query("DELETE FROM account WHERE id IS :id")
    suspend fun deleteAccountToo(id: Int)

    @Query("SELECT * FROM account WHERE username = :username")
    suspend fun getUserByUsername(username: String) : Account?

    ////////////////  ////////////////  ////////////////
    /// Account ////  ///// And //////  /// Profile ////
    ////////////////  ////////////////  ////////////////

    @Transaction
    @Query("SELECT * FROM profile INNER JOIN account ON profile.id = account.id WHERE profile.id = :id")
    fun getProfileAndAccountById(id: Int): LiveData<ProfileAndAccount>
}


////////////////  ////////////////  ////////////////
////////////////  /// Profile ////  ////////////////
////////////////  ////////////////  ////////////////


////////////////  ////////////////  ////////////////
////////////////  /// Account ////  ////////////////
////////////////  ////////////////  ////////////////

////////////////  ////////////////  ////////////////
/// Account ////  ///// And //////  /// Profile ////
////////////////  ////////////////  ////////////////