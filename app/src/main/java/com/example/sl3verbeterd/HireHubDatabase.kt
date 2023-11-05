package com.example.sl3verbeterd

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [
       Account::class,
        Profile::class],
    version = 1
)
abstract class HireHubDatabase: RoomDatabase() {

    //  abstract fun daoPro(): ProfileDao
    abstract val hireHubDao: HireHubDao
    // abstract val accPro: AccountDao

    companion object {
        //        Volatile indicates that whenever there takes place any changes to this INSTANCE object immediately
//        it indicates to this all the threads using this instance ;)
        @Volatile
        private var INSTANCE: HireHubDatabase? = null

        fun getInstance(context: Context): HireHubDatabase {
            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    HireHubDatabase::class.java,
                    "TheDB"
                ).build().also {
                    INSTANCE = it
                }
            }
        }


    }
}