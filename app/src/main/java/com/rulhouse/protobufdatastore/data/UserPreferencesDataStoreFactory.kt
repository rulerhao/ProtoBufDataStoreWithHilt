package com.rulhouse.protobufdatastore.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import androidx.datastore.migrations.SharedPreferencesMigration
import androidx.datastore.migrations.SharedPreferencesView
import com.rulhouse.protobufdatastore.datastore.UserPreferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

class UserPreferencesDataStoreFactory {
    companion object {
        private const val USER_PREFERENCES_NAME = "user_preferences"
        private const val DATA_STORE_FILE_NAME = "user_prefs.pb"

        fun create(context: Context): DataStore<UserPreferences> {
            return DataStoreFactory.create(
                serializer = UserPreferencesSerializer,
                produceFile = { context.dataStoreFile(DATA_STORE_FILE_NAME) },
                corruptionHandler = null,
                migrations = listOf(
                    SharedPreferencesMigration(
                        context = context,
                        sharedPreferencesName = USER_PREFERENCES_NAME
                    ) { sharedPrefs: SharedPreferencesView, currentData: UserPreferences ->
                        // Define the mapping from SharedPreferences to UserPreferences
                        currentData.toBuilder().showCompleted = sharedPrefs.getBoolean("show_completed", false)
                        currentData
                    }
                ),
                scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
            )
        }
    }
}