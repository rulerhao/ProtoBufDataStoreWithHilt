package com.rulhouse.protobufdatastore

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun MainScreen (
    viewModel: MainScreenViewModel = hiltViewModel()
) {
    val showCompleted = viewModel.showCompleted.value

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
        ) {
            Text(
                text = showCompleted.toString()
            )
            Button(
                onClick = {
                    viewModel.onEvent(
                        MainScreenEvent.ToggleShowCompleted
                    )
                }
            ) {
                Text(
                    text = "Toggle show completed"
                )
            }
        }
    }
}