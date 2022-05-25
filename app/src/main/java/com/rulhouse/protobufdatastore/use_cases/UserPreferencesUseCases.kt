package com.rulhouse.protobufdatastore.use_cases

data class UserPreferencesUseCases (
    val getUserPreferencesFlow: GetUserPreferencesFlow,
    val updateShowCompleted: UpdateShowCompleted
)