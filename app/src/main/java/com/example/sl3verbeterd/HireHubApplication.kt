package com.example.sl3verbeterd

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
class HireHubApplication : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())
    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { HireHubDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { HireHubRepository(database.hireHubDao()) }
}