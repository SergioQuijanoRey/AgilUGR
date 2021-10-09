package com.project.agilugr.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.interaction.FocusInteraction
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.project.agilugr.MockFocusAPI
import com.project.agilugr.ui.views.FocusModeSelector
import com.project.agilugr.ui.views.FocusModeSessionView
import kotlin.time.ExperimentalTime

// TODO -- este codigo esta muy mal diseñado
@RequiresApi(Build.VERSION_CODES.O)
@ExperimentalTime
@Composable
fun navigation(){
    val navController = rememberNavController()

    // El NavHost define la forma en la que navegamos nuestras vistas
    NavHost(
        navController = navController,
        startDestination = "focus_mode_selector"
    ){

        // Vista del selector de configuraciones del focus mode
        composable(route = "focus_mode_selector"){
            FocusModeSelector(MockFocusAPI.getMockFocusAPI(), navController = navController ).getView()
        }

        // Vista desde dentro de una sesion de focus mode
        composable(route = "focus_mode_session"){
            FocusModeSessionView(MockFocusAPI.getMockFocusAPI(), navController = navController).getView()
        }
    }
}