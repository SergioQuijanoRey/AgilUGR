package com.project.agilugr.ui.views

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.project.agilugr.FocusAPI
import com.project.agilugr.ui.theme.AgilUGRTheme
import com.project.agilugr.utils.phone_dimensions
import kotlinx.coroutines.delay
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

/** Clase para representar la vista dentro del modo focus, cuando estamos corriendo una sesion */
class FocusModeSessionView(val focus_api: FocusAPI, val navController: NavController) {
    /** Devuelve los elementos de compose que componen esta vista */
    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun getView(){

        Column(
            // Esta columna, que engloba a toda nuestra vista, la centramos en la pantalla
            modifier = Modifier
                .fillMaxSize()

                // Para centrar la columna verticalmente en la pantalla
                // Bajamos la mitad del tamaño de la pantalla
                .padding(vertical = phone_dimensions().getHalfHeightDP()),

            // Damos espacio vertical entre los elementos
            verticalArrangement = Arrangement.spacedBy(10.dp),

            // Centramos la columna horizontalmente en la pantalla
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            mainBox(focus_api)
            Row {
                stopButton(
                    backgroundColor =  MaterialTheme.colors.primary,
                    contentColor = MaterialTheme.colors.onPrimary,
                    modifier = Modifier
                        .padding(10.dp)
                        .height(50.dp)
                        .width(80.dp)
                )
                exitButton(
                    backgroundColor = MaterialTheme.colors.primary,
                    contentColor = MaterialTheme.colors.onPrimary,
                    modifier = Modifier
                        .padding(10.dp)
                        .height(50.dp)
                        .width(80.dp),
                    navControler = navController8
                )
            }
        }

    }
}

/** Esta funcion dibuja la caja central de esta vista*/
// TODO -- estoy poniendo mucha logica en una vista
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun mainBox(api: FocusAPI){
    // En primer lugar consulto los valores que me hacen falta para la esta vista
    // Tomo la configuracion asociada a la sesion que esta corriendo ahora y la sesion en si
    // que esta corriendo
    val config = api.getRunningFocusConfig()!!
    val session = api.getRunningFocusMode()!!

    // Tomo la duracion prevista para la sesion
    var duration = config.duration
    val duration_hours = duration.hours
    val duration_minutes = duration.minutes
    val duration_seconds = duration.seconds

    // Tomo el tiempo en el que empezo la sesion y el tiempo que llevamos dentro de la sesion
    // Uso remember para poder modificar el estado de la variable en la vista
    val start_time = session.start_time
    val running_minutes by remember { mutableStateOf(session.getRunningMinutes()) }

    // Muestro los valores
    Column{
        Text(
            text = "En modo focus durante $duration_hours:$duration_minutes:$duration_seconds",
            color = MaterialTheme.colors.onBackground,
            style = MaterialTheme.typography.h6
        )
        Text(
            text = "La sesión empezó a las $start_time",
            color = MaterialTheme.colors.onBackground,
            style = MaterialTheme.typography.h6
        )
        Text(
            text = "Llevas $running_minutes minutos en modo focus",
            color = MaterialTheme.colors.onBackground,
            style = MaterialTheme.typography.h6
        )
    }

}

/** Funcion que coloca el boton para parar la sesion */
// TODO -- deberiamos estar usando el tema del sistema, y no ciertos colores
@Composable
fun stopButton(backgroundColor: Color, contentColor: Color, modifier: Modifier){
    AgilUGRTheme {
        Button(
            onClick = {
                // TODO -- no estamos haciendo nada
            },
            colors = ButtonDefaults.textButtonColors(
                backgroundColor = backgroundColor,
                contentColor = contentColor
            ),
            modifier = modifier
        ){
            Text("Stop")
        }
    }
}

// TODO -- en vez de pasar el navcontroller como parametro, pasar una lambda que realice dicha accion
/** Funcion que coloca el boton para salir de la sesion*/
@Composable
fun exitButton(backgroundColor: Color, contentColor: Color, modifier: Modifier, navControler: NavController){
    Button(
        onClick = {
            navControler.navigate("focus_mode_selector")
        },
        colors = ButtonDefaults.textButtonColors(
            backgroundColor = backgroundColor,
            contentColor = contentColor
        ),
        modifier = modifier
    ){
        Text("Exit")
    }
}