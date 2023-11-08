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
import kotlinx.coroutines.flow.Flow

@Dao
interface HireHubDao {

    // Combination of Insert and Update
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


    @Query("DELETE FROM account WHERE id IS :id")
    suspend fun deleteAccountToo(id: Int)

//    @Query("SELECT * FROM profile")
//    fun showProfile(id: Int) : LiveData<Profile>


    @Update
    suspend fun updateProfile(profile: Profile)

    //Custom Queries

    // Flow is a data structure that observes any changes in the list.
    // And updates the list.
    @Query("SELECT * FROM profile ORDER BY firstName ASC")
    fun getProfilesOrderedByFirstName(): Flow<List<Profile>>

    @Query("SELECT * FROM profile ORDER BY lastName ASC")
    fun getProfilesOrderedByLastName(): Flow<List<Profile>>



//    @Query("UPDATE profile SET firstName=:firstName WHERE id LIKE :id")
//    suspend fun updateProfile(firstName: String, id: Int)
    @Query("SELECT * FROM account WHERE username = :username")
    suspend fun getUserByUsername(username: String) : Account?


//    @Query("SELECT * FROM account ORDER BY username ASC")
//    fun getAccountsOrderedByFirstName(): Flow<List<Profile>>





    // Combination of Insert and Update
    @Upsert
    suspend fun upsertAccount(account: Account)

    @Delete
    suspend fun deleteAccount(account: Account)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAccount(account: Account)

    @Update
    suspend fun updateAccount(account: Account)



    //Custom Queries

    // Flow is a data structure that observes any changes in the list.
    // And updates the list.
//    @Query("SELECT * FROM Account ORDER BY firstName ASC")
//    fun getAccountsOrderedByFirstName(): Flow<List<Account>>
//
//    @Query("SELECT * FROM Account ORDER BY lastName ASC")
//    fun getAccountsOrderedByLastName(): Flow<List<Account>>
//
//    @Query("SELECT * FROM Account ORDER BY phoneNumber ASC")
//    fun getAccountsOrderedByPhoneNumber(): Flow<List<Account>>
//
//
//    @Query("UPDATE Account SET firstName=:firstName WHERE id LIKE :id")
//    suspend fun updateAccount(firstName: String, id: Int)
}