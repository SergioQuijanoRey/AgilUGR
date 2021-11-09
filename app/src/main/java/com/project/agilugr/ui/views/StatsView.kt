package com.project.agilugr.ui.views

import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.project.agilugr.ui.navigation.NavigationDirector

/** Vista para mostrar las estadisticas de un usuario*/
class StatsView(navigationDirector: NavigationDirector) {

    @Composable
    fun getView(){
        OutlinedButton(onClick = {}) {
            Text("Vista de estadisticas")
        }

    }
}