package com.project.agilugr.gestures

import android.view.MotionEvent
import androidx.activity.ComponentActivity
import androidx.core.view.MotionEventCompat
import com.project.agilugr.ui.navigation.NavigationDirector
import com.project.agilugr.ui.navigation.NavigationMapper

/** Interfaz que define lo que debe implementar un manager de gestos */
interface GestureManager{

    /** Procesa un evento sobre la pantalla del dispositivo */
    fun onTouch(event: MotionEvent): Boolean
}

/**
 * Clase para manejar los eventos generados por los gestos
 * @param navigationDirector para poder navegar a los distintos puntos de nuestra aplicacion
 *
 * Notar que tambien podemos gestionar los eventos a nivel de cada componente de Jetpack Compose
 * */
class DefaultGestureManager(val navigationDirector: NavigationDirector): GestureManager, ComponentActivity()  {

    /**
     *  Define como gestionamos los eventos de dedos sobre la pantalla
     *
     * @param event el evento que tenemos que procesar
     * */
    override fun onTouch(event: MotionEvent): Boolean{
        val action: Int = MotionEventCompat.getActionMasked(event)

        return when (action) {
            MotionEvent.ACTION_DOWN -> {
                true
            }
            MotionEvent.ACTION_MOVE -> {
                true
            }
            MotionEvent.ACTION_UP -> {
                this.navigationDirector.navigate(NavigationMapper.FOCUS_MODE_SESSION)
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