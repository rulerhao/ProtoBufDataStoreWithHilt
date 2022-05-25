package com.rulhouse.protobufdatastore.data

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import com.rulhouse.protobufdatastore.datastore.UserPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import java.io.IOException

interface UserPreferencesRepository {
    fun getUserPreferencesFlow(): Flow<UserPreferences>

    suspend fun updateShowCompleted(completed: Boolean)

    suspend fun fetchInitialPreferences(): UserPreferences
}