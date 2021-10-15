package com.project.agilugr

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import com.project.agilugr.ui.navigation.NavigationDirector
import com.project.agilugr.ui.theme.AgilUGRTheme
import kotlin.time.ExperimentalTime

@ExperimentalTime
class MainActivity : ComponentActivity() {

    // APIs que vamos a consumir para tomar los datos del backend
    val focus_api = MockFocusAPI.getMockFocusAPI()

    // Tomamos el director de navegacion para que lance la interfaz grafica
    val navigation_director = NavigationDirector(focus_api = focus_api)

    // Funcion principal
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        // Llamamos al onCreate del parent
        super.onCreate(savedInstanceState)

        // Establecemos la UI de la aplicacion
        setContent {
            AgilUGRTheme {

                // Usamos el director de navegacion para lanzar la interfaz grafica
                navigation_director.buildNavigationAndStartUI()
            }
        }
    }

}



