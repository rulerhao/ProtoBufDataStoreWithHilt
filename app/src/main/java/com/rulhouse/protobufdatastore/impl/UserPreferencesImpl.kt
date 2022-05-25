package com.rulhouse.protobufdatastore.impl

import androidx.datastore.core.DataStore
import com.rulhouse.protobufdatastore.data.UserPreferencesRepository
import com.rulhouse.protobufdatastore.datastore.UserPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import java.io.IOException

class UserPreferencesImpl(
    private val userPreferencesStore: DataStore<UserPreferences>
): UserPreferencesRepository {

    override fun getUserPreferencesFlow(): Flow<UserPreferences> {
        return userPreferencesStore.data
            .catch { exception ->
                // dataStore.data throws an IOException when an error is encountered when reading data
                if (exception is IOException) {
                    emit(UserPreferences.getDefaultInstance())
                } else {
                    throw exception
                }
            }
    }

    override suspend fun updateShowCompleted(completed: Boolean) {
        userPreferencesStore.updateData { preferences ->
            preferences.toBuilder().setShowCompleted(completed).build()
        }
    }

    override suspend fun fetchInitialPreferences() = userPreferencesStore.data.first()
}