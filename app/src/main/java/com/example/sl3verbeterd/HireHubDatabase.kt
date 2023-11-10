package com.example.sl3verbeterd

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [
       Account::class,
        Profile::class],
    version = 1
)
abstract class HireHubDatabase: RoomDatabase() {

    abstract fun hireHubDao(): HireHubDao

    companion object {

        @Volatile
        private var INSTANCE: HireHubDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): HireHubDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HireHubDatabase::class.java,
                    "HireHubDatabase"
                )

                    .fallbackToDestructiveMigration()
                    .addCallback(HireHubDatabaseCallback(scope))
                    .build()
                INSTANCE = instance

                instance
            }
        }


        private class HireHubDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {

            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.hireHubDao())
                    }
                }
            }
        }



        suspend fun populateDatabase(hireHubDao: HireHubDao) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            hireHubDao.deleteAll()

            // Admin
                // Admin

                var account = Account("Admin", "Admin123", "admin")
                hireHubDao.insertAccount(account)
                var profile = Profile("René", "Prinz", "Rotterdam", "Docent ICT", "HBO", true)
                hireHubDao.insertProfile(profile)

            // Recruiter

            account = Account("Recruiter", "Recruiter123", "recruiter")
            hireHubDao.insertAccount(account)
            profile = Profile("Polina", "Kozlova", "Almere", "Docent ICT", "HBO", true)
            hireHubDao.insertProfile(profile)

            // User
                // User 1
            account = Account("Gebruiker", "Gebruiker123", "user")
            hireHubDao.insertAccount(account)
            profile = Profile("Jan", "Zuur", "Utrecht", "Software Developer", "MBO", true)
            hireHubDao.insertProfile(profile)

                // User 2
            account = Account("Jaap", "Jaap123", "user")
            hireHubDao.insertAccount(account)
            profile = Profile("Jaap", "Grotebroek", "Amsterdam", "Helpdesk", "HBO", true)
            hireHubDao.insertProfile(profile)

                // User 3
            account = Account("Douwe", "Douwe123", "user")
            hireHubDao.insertAccount(account)
            profile = Profile("Douwe", "van Velden", "Almere", "Verkoper", "WO", false)
            hireHubDao.insertProfile(profile)

                // User 4
            account = Account("Esmee", "Esmee123", "user")
            hireHubDao.insertAccount(account)
            profile = Profile("Esmee", "van de Twee", "Zoetermeer", "Software Developer", "MBO", true)
            hireHubDao.insertProfile(profile)

                // User 5
            account = Account("Rudy", "Rudy123", "user")
            hireHubDao.insertAccount(account)
            profile = Profile("Rudy", "Dondersteen", "Bleiswijk", "Software Developer", "Middelbare School", false)
            hireHubDao.insertProfile(profile)

                // User 6
            account = Account("Paula", "Paula123", "user")
            hireHubDao.insertAccount(account)
            profile = Profile("Paula", "Mel", "De Gooi", "DevOpser", "MBO", true)
            hireHubDao.insertProfile(profile)

                // User 7
            account = Account("Jeffrey", "Jeffrey123", "user")
            hireHubDao.insertAccount(account)
            profile = Profile("Jeffrey", "Tang", "Almere-Stad", "Designer", "Basisschool", true)
            hireHubDao.insertProfile(profile)

                // User 8
            account = Account("Charlie", "Charlie123", "user")
            hireHubDao.insertAccount(account)
            profile = Profile("Charlie", "der Boom", "Almere-Buiten", "Tester", "Zelf", false)
            hireHubDao.insertProfile(profile)

                // User 9
            account = Account("Yassine", "Yassine123", "user")
            hireHubDao.insertAccount(account)
            profile = Profile("Yassine", "Messaoudi", "Maastricht", "Software Developer", "Youtube", true)
            hireHubDao.insertProfile(profile)

                // User 10
            account = Account("Jos", "Jos123", "user")
            hireHubDao.insertAccount(account)
            profile = Profile("Jos", "Hamer", "Den Haag", "UI Designer", "Basisschool", false)
            hireHubDao.insertProfile(profile)


        }


    }


}