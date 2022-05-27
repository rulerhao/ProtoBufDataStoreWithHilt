package com.rulhouse.protobufdatastore.di

import android.content.Context
import androidx.datastore.core.DataMigration
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.dataStore
import androidx.datastore.dataStoreFile
import androidx.datastore.migrations.SharedPreferencesMigration
import androidx.datastore.migrations.SharedPreferencesView
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.room.migration.Migration
import com.rulhouse.protobufdatastore.data.UserPreferencesDataStoreFactory
import com.rulhouse.protobufdatastore.data.UserPreferencesRepository
import com.rulhouse.protobufdatastore.data.UserPreferencesSerializer
import com.rulhouse.protobufdatastore.datastore.UserPreferences
import com.rulhouse.protobufdatastore.impl.UserPreferencesImpl
import com.rulhouse.protobufdatastore.use_cases.GetUserPreferencesFlow
import com.rulhouse.protobufdatastore.use_cases.UpdateShowCompleted
import com.rulhouse.protobufdatastore.use_cases.UserPreferencesUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.first
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideProtoDataStore(@ApplicationContext appContext: Context): DataStore<UserPreferences> {
        return UserPreferencesDataStoreFactory.create(appContext)
    }

    @Provides
    @Singleton
    fun provideUserPrefsRepository(dataStore: DataStore<UserPreferences>): UserPreferencesRepository {
        return UserPreferencesImpl(dataStore)
    }

    @Provides
    @Singleton
    fun provideSharedPreferencesUseCases(repository: UserPreferencesRepository): UserPreferencesUseCases {
        return UserPreferencesUseCases(
            getUserPreferencesFlow = GetUserPreferencesFlow(repository),
            updateShowCompleted = UpdateShowCompleted(repository)
        )
    }
}