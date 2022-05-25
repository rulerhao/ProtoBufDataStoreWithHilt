package com.rulhouse.protobufdatastore

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import androidx.datastore.migrations.SharedPreferencesMigration
import androidx.datastore.migrations.SharedPreferencesView
import com.google.protobuf.InvalidProtocolBufferException
import com.rulhouse.protobufdatastore.data.UserPreferencesRepository
import com.rulhouse.protobufdatastore.data.UserPreferencesSerializer
import com.rulhouse.protobufdatastore.datastore.UserPreferences
import com.rulhouse.protobufdatastore.ui.theme.ProtoBufDataStoreTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.io.InputStream
import java.io.OutputStream


private val USER_PREFERENCES_NAME = "user_preferences"
private val DATA_STORE_FILE_NAME = "user_prefs.pb"
private val SORT_ORDER_KEY = "sort_order"

private val Context.userPreferencesStore: DataStore<UserPreferences> by dataStore(
    fileName = DATA_STORE_FILE_NAME,
    serializer = UserPreferencesSerializer,
    produceMigrations = { context ->
        listOf(
            SharedPreferencesMigration(
                context,
                USER_PREFERENCES_NAME,
            ) { sharedPrefs: SharedPreferencesView, currentData: UserPreferences ->
                // Define the mapping from SharedPreferences to UserPreferences

//                if (currentData.sortOrder == SortOrder.UNSPECIFIED) {
//                    currentData.toBuilder().setSortOrder(
//                        SortOrder.valueOf(
//                            sharedPrefs.getString(SORT_ORDER_KEY, SortOrder.NONE.name)!!
//                        )
//                    ).build()
//                } else {
                    currentData
//                }
            }
        )
    }
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProtoBufDataStoreTheme {
                MainScreen()
            }
        }
    }
}