package com.rulhouse.protobufdatastore.use_cases

import com.rulhouse.protobufdatastore.data.UserPreferencesRepository
import com.rulhouse.protobufdatastore.datastore.UserPreferences
import kotlinx.coroutines.flow.Flow

class GetUserPreferencesFlow (
    private val repository: UserPreferencesRepository
) {
    suspend operator fun invoke(): Flow<UserPreferences> {
        return repository.getUserPreferencesFlow()
    }
}