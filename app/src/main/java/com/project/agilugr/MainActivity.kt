package com.project.agilugr

import android.app.Activity
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import com.project.agilugr.ui.theme.AgilUGRTheme
import com.project.agilugr.ui.views.FocusModeSelector
import com.project.agilugr.ui.views.FocusModeSessionView
import kotlin.time.ExperimentalTime

@ExperimentalTime
class MainActivity : ComponentActivity() {

    // API que vamos a usar para interactuar con la sesion del modo focus
    val focus_api_interface: FocusAPI = MockFocusAPI.getMockFocusAPI()

    // Distintas views de nuestra aplicacion
    val focus_mode_session_view = FocusModeSessionView(this.focus_api_interface)
    val focus_mode_selector_view = FocusModeSelector(this.focus_api_interface)

    // Funcion principal
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        // Llamamos al onCreate del parent
        super.onCreate(savedInstanceState)

        // Establecemos la UI de la aplicacion
        setContent {
            AgilUGRTheme {
                // Vista desde la que iniciamos la aplicacion
                // TODO -- necesitamos una navegacion entre las distintas vistas
                focus_mode_selector_view.getView()
            }
        }
    }
}
