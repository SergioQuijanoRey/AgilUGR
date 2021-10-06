package com.project.agilugr.ui.views

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.project.agilugr.FocusAPIInterface

/** Clase para representar la vista dentro del modo focus, cuando estamos corriendo una sesion */
class FocusModeSessionView(val focus_api: FocusAPIInterface) {
    /** Devuelve los elementos de compose que componen esta vista */
    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun getView(){
        Column{
            mainBox(focus_api)
            Row{
                stopButton()
                exitButton()
            }
        }
    }
}

/** Esta funcion dibuja la caja central de esta vista*/
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun mainBox(api: FocusAPIInterface){
    // En primer lugar consulto los valores que me hacen falta para la interfaz
    var config = api.getRunningFocusConfig()!!
    var duration = config.duration
    var session = api.getRunningFocusMode()!!
    var start_time = session.start_time

    Column{
        Text(text = "In study mode for $duration")
        Text(text = "$start_time")
    }

}

/** Funcion que coloca el boton para parar la sesion */
@Composable
fun stopButton(){
    Button(
        onClick = {
            // TODO -- no estamos haciendo nada
        },
        colors = ButtonDefaults.textButtonColors(
            backgroundColor = Color.Blue
        )
    ){
        Text("Stop")
    }

}

/** Funcion que coloca el boton para salir de la sesion*/
@Composable
fun exitButton(){
    Button(
        onClick = {
            // TODO -- no estamos haciendo nada
        },
        colors = ButtonDefaults.textButtonColors(
            backgroundColor = Color.Blue
        )
    ){
        Text("Exit")
    }
}
