package com.project.agilugr.ui.views

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.project.agilugr.FocusAPIInterface
import com.project.agilugr.ui.theme.AgilUGRTheme
import java.time.LocalTime
import java.time.temporal.ChronoUnit

/** Clase para representar la vista dentro del modo focus, cuando estamos corriendo una sesion */
class FocusModeSessionView(val focus_api: FocusAPIInterface) {
    /** Devuelve los elementos de compose que componen esta vista */
    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun getView(){
        Column {
            mainBox(focus_api)
            Row {
                stopButton(backgroundColor =  MaterialTheme.colors.primary)
                exitButton(backgroundColor = MaterialTheme.colors.primary)
            }
        }
    }
}

/** Esta funcion dibuja la caja central de esta vista*/
// TODO -- estoy poniendo mucha logica en una vista
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun mainBox(api: FocusAPIInterface){
    // En primer lugar consulto los valores que me hacen falta para la interfaz
    // Tomo la configuracion asociada a la sesion que esta corriendo ahora
    val config = api.getRunningFocusConfig()!!

    // Tomo la duracion prevista para la sesion
    var duration = config.duration
    val duration_hours = duration.hours
    val duration_minutes = duration.minutes
    val duration_seconds = duration.seconds

    // Tomo el tiempo en el que empezo la sesion
    val session = api.getRunningFocusMode()!!
    val start_time = session.start_time

    // Calculo cuanto tiempo llevamos estudiando
    val now_time = LocalTime.now()
    val current_minutes = ChronoUnit.MINUTES.between(now_time, start_time)


    // Muestro los valores
    Column{
        Text(text = "En modo focus durante $duration_hours:$duration_minutes:$duration_seconds")
        Text(text = "La sesión empezó a las $start_time")
        Text(text = "Llevas $current_minutes minutos en modo focus")
    }

}

/** Funcion que coloca el boton para parar la sesion */
// TODO -- deberiamos estar usando el tema del sistema, y no ciertos colores
@Composable
fun stopButton(backgroundColor: Color){
    AgilUGRTheme {
        Button(
            onClick = {
                // TODO -- no estamos haciendo nada
            },
            colors = ButtonDefaults.textButtonColors(
                backgroundColor = backgroundColor
            )
        ){
            Text("Stop")
        }
    }
}

/** Funcion que coloca el boton para salir de la sesion*/
@Composable
fun exitButton(backgroundColor: Color){
    Button(
        onClick = {
            // TODO -- no estamos haciendo nada
        },
        colors = ButtonDefaults.textButtonColors(
            backgroundColor = backgroundColor
        )
    ){
        Text("Exit")
    }
}
