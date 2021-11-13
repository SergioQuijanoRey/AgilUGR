package com.project.agilugr.ui.views

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
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
import com.project.agilugr.utils.MainBackground
import com.project.agilugr.utils.events
import com.project.agilugr.utils.headerBackground
import com.project.agilugr.utils.stopevent
import kotlinx.coroutines.delay
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

class FocusModeSessionView(val focus_api: FocusAPI, val navController: NavController) {
    /** Devuelve los elementos de compose que componen esta vista */
    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun getView() {
        val config = focus_api.getRunningFocusConfig()!!
        val session = focus_api.getRunningFocusMode()!!
        var duration = config.duration
        val duration_minutes = duration.minutes

        Surface(
            color = Color(MainBackground),
            modifier = Modifier.fillMaxWidth()
                .height(600.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                // call the function Timer
                // and pass the values
                // it is defined below.
                Timer(
                    minutes = duration_minutes* 1000L,
                    seconds = duration.seconds*1000L,
                    handleColor = Color(headerBackground),
                    inactiveBarColor = Color.LightGray,
                    activeBarColor = Color(events),
                    modifier = Modifier.size(200.dp)
                )
            }
        }
    }
}

@Composable
fun Timer(
    // Tiempo del temporizador
    minutes: Long,
    seconds: Long,

    // Color del arco
    handleColor: Color,

    // Barra de progreso
    inactiveBarColor: Color,

    // Color de barra activa
    activeBarColor: Color,
    modifier: Modifier = Modifier,

    // Valores iniciales
    initialValue: Float = 1f,
    strokeWidth: Dp = 5.dp
) {
    //Variable size
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    // Variable para el valor
    var value by remember {
        mutableStateOf(initialValue)
    }
    // variable para el tiempo en minutos actual
    var currentMinutes by remember {
        mutableStateOf(minutes)
    }
    // Variable para el tiempo en segundos actual
    var currentSeconds by remember {
        mutableStateOf(seconds)
    }
    // Variable para saber si el tiempo está corriendo o no
    var isTimerRunning by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = currentMinutes, key2=currentSeconds, key3 = isTimerRunning) {
        if(currentMinutes >= 0 && isTimerRunning) {
            if (currentSeconds>0) {
                delay(100L)
                currentSeconds -= 100L
                value = currentMinutes / (minutes*60).toFloat() //Revisar esto
            }else {
                delay(100L)
                currentMinutes -= 100L
                currentSeconds=60L * 1000L
                value = currentMinutes / (minutes*60).toFloat()
            }
        }
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .onSizeChanged {
                size = it
            }
    ) {
        // Dibujamos el temporizador
        Canvas(modifier = modifier) {
            // dibujamos el arco
            drawArc(
                color = inactiveBarColor, // Asignamos color para la barra de progreso
                startAngle = -215f, // ángulo de inicio
                sweepAngle = 250f, // ángulo final
                useCenter = false,
                size = Size(size.width.toFloat(), size.height.toFloat()),
                // Hacer redondeado los bordes del arco
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
            // dibujamos arco de progreso
            drawArc(
                color = activeBarColor,
                startAngle = -215f,
                sweepAngle = 250f * value,
                useCenter = false,
                size = Size(size.width.toFloat(), size.height.toFloat()),
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
            // Calculamos la posición del puntero del arco de progreso
            val center = Offset(size.width / 2f, size.height / 2f)
            val beta = (250f * value + 145f) * (PI / 180f).toFloat()
            val r = size.width / 2f
            val a = cos(beta) * r
            val b = sin(beta) * r
            drawPoints(
                listOf(Offset(center.x + a, center.y + b)),
                pointMode = PointMode.Points,
                color = handleColor,
                strokeWidth = (strokeWidth * 3f).toPx(),
                cap = StrokeCap.Round  // make the pointer round
            )
        }
        // Texto del temporizador
        Text(
            text = (currentMinutes / 1000L).toString()+":"+(currentSeconds / 1000L).toString(),
            fontSize = 44.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        // Boton para inicio y parada del temporizador
        Button(
            onClick = {
                if(currentMinutes <= 0L) {
                    currentMinutes = minutes
                    isTimerRunning = true
                } else {
                    isTimerRunning = !isTimerRunning
                }
            },
            modifier = Modifier.align(Alignment.BottomCenter),
            // change button color
            colors = ButtonDefaults.buttonColors(
                backgroundColor = if (!isTimerRunning || (currentMinutes <= 0L || currentSeconds<=0L)) {
                    Color(events)
                } else {
                    Color(stopevent)
                }
            )
        ) {
            Text(
                //Cambia el texto dependiendo de si el tiempo pasa o no
                text = if (isTimerRunning && (currentMinutes >= 0L || currentSeconds >= 0L)) "Stop"
                else if (!isTimerRunning && (currentMinutes >= 0L || currentSeconds >= 0L)) "Start"
                else "Restart"
            )
        }
    }
}