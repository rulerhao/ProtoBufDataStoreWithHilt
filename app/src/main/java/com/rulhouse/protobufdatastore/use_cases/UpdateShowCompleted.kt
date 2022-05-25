package com.rulhouse.protobufdatastore.use_cases

import com.rulhouse.protobufdatastore.data.UserPreferencesRepository
import com.rulhouse.protobufdatastore.datastore.UserPreferences
import kotlinx.coroutines.flow.Flow

class UpdateShowCompleted (
    private val repository: UserPreferencesRepository
) {
    suspend operator fun invoke(completed: Boolean) {
        return repository.updateShowCompleted(completed)
    }
}