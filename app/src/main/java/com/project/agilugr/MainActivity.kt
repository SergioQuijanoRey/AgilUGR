package com.project.agilugr

import android.os.Build
import android.os.Bundle
import android.view.MotionEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.MotionEventCompat
import com.project.agilugr.ui.navigation.NavigationDirector
import com.project.agilugr.ui.navigation.NavigationMapper
import com.project.agilugr.ui.theme.AgilUGRTheme
import com.project.agilugr.ui.views.FocusModeSessionView
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

        // TODO -- mover a una gestion de eventos propia
        override fun onTouchEvent(event: MotionEvent): Boolean {

            val action: Int = MotionEventCompat.getActionMasked(event)

            return when (action) {
                MotionEvent.ACTION_DOWN -> {
                    true
                }
                MotionEvent.ACTION_MOVE -> {
                    true
                }
                MotionEvent.ACTION_UP -> {
                    this.navigation_director.navigate(NavigationMapper.FOCUS_MODE_SESSION)
                    true
                }
                MotionEvent.ACTION_CANCEL -> {
                    true
                }
                MotionEvent.ACTION_OUTSIDE -> {
                    true
                }
                else -> super.onTouchEvent(event)
            }
        }


}


