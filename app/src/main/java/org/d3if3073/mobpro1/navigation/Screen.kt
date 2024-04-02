package org.d3if3073.mobpro1.navigation

import android.health.connect.datatypes.ExerciseRoute

sealed class Screen (val route: String) {
    data object Home: Screen("mainScreen")
    data object About: Screen("aboutScreen")
    data object Haus: Screen("hausScreen")
}