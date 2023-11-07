package com.example.sl3verbeterd

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.sl3verbeterd.ui.applicant.ApplicantsFragment
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

    //  abstract fun daoPro(): ProfileDao
//    abstract val hireHubDao: HireHubDao
    // abstract val accPro: AccountDao
    abstract fun hireHubDao(): HireHubDao

    companion object {
        //        Volatile indicates that whenever there takes place any changes to this INSTANCE object immediately
//        it indicates to this all the threads using this instance ;)
        @Volatile
        private var INSTANCE: HireHubDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): HireHubDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HireHubDatabase::class.java,
                    "HireHubDatabase"
                )
                    // Wipes and rebuilds instead of migrating if no Migration object.
                    // Migration is not part of this codelab.
                    .fallbackToDestructiveMigration()
                    .addCallback(HireHubDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

        private class HireHubDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            /**
             * Override the onCreate method to populate the database.
             */
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.hireHubDao())
                    }
                }
            }
        }

        /**
         * Populate the database in a new coroutine.
         * If you want to start with more words, just add them.
         */
        suspend fun populateDatabase(hireHubDao: HireHubDao) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            hireHubDao.deleteAll()

           var profile = Profile("Jeffrey", "Tang", "07")
          hireHubDao.insertProfile(profile)
            profile = Profile("Yass", "Messa", "07")
            hireHubDao.insertProfile(profile)
            profile = Profile("Yass", "Messa", "07")
            hireHubDao.insertProfile(profile)
            profile = Profile("Yass", "Messa", "07")
            hireHubDao.insertProfile(profile)
            profile = Profile("Yass", "Messa", "07")
            hireHubDao.insertProfile(profile)
            profile = Profile("Yass", "Messa", "07")
            hireHubDao.insertProfile(profile)
        }



//        fun getInstance(context: Context): HireHubDatabase {
//            synchronized(this) {
//                return INSTANCE ?: Room.databaseBuilder(
//                    context.applicationContext,
//                    HireHubDatabase::class.java,
//                    "HireHubDatabase"
//                ).build().also {
//                    INSTANCE = it
//                }
//            }
//        }


    }
}