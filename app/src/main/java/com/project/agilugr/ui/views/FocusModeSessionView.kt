package com.project.agilugr.ui.views

import android.os.Build
import android.view.MotionEvent
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.MotionEventCompat
import androidx.navigation.NavController
import com.project.agilugr.FocusAPI
import com.project.agilugr.MockFocusAPI
import com.project.agilugr.ui.navigation.NavigationDirector
import com.project.agilugr.ui.navigation.NavigationMapper
import com.project.agilugr.ui.theme.AgilUGRTheme
import com.project.agilugr.utils.phone_dimensions
import kotlinx.coroutines.delay
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin
import kotlin.time.ExperimentalTime

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
                        .width(80.dp),

                    // TODO -- no estamos haciendo ninguna accion con este boton
                    on_click = {}
                )
                exitButton(
                    backgroundColor = MaterialTheme.colors.primary,
                    contentColor = MaterialTheme.colors.onPrimary,
                    modifier = Modifier
                        .padding(10.dp)
                        .height(50.dp)
                        .width(80.dp),

                    // Cuando hago click, navego al selector del modo focus
                    // Cambiando la lambda podemos cambiar a donde navegamos en el exit
                    on_click = { navController.navigate(NavigationMapper.FOCUS_MODE_SELECTOR.route) }
                )
            }
        }
    }
}

/** Esta funcion dibuja la caja central de esta vista*/
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
@Composable
fun stopButton(backgroundColor: Color, contentColor: Color, modifier: Modifier, on_click: () -> Unit){
    AgilUGRTheme {
        Button(
            onClick = {
                on_click()
            },
            colors = ButtonDefaults.textButtonColors(
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.onPrimary,
            ),
            modifier = modifier
        ){
            Text("Stop")
        }
    }
}

/**
 * Funcion que coloca el boton para salir de la sesion
 *
 * @param on_click funcion a la que llamamos para hacer click. Para manejar la navegacion
 * */
@Composable
fun exitButton(backgroundColor: Color, contentColor: Color, modifier: Modifier, on_click: () -> Unit){
    Button(
        onClick = {
            on_click()
        },
        colors = ButtonDefaults.textButtonColors(
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.onPrimary
        ),
        modifier = modifier
    ){
        Text("Exit")
    }
}