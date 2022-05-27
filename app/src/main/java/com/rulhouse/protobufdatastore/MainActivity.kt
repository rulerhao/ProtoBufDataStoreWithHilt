package com.rulhouse.protobufdatastore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.rulhouse.protobufdatastore.ui.theme.ProtoBufDataStoreTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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