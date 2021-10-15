package com.project.agilugr.gestures

import android.os.Build
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import androidx.core.view.GestureDetectorCompat
import androidx.core.view.MotionEventCompat
import com.project.agilugr.ui.navigation.NavigationDirector
import com.project.agilugr.ui.navigation.NavigationMapper

/** Interfaz que define lo que debe implementar un manager de gestos */
interface GestureManager{

    /** Procesa un evento simple sobre la pantalla del dispositivo */
    fun onTouch(event: MotionEvent): Boolean

    /**
     * Gestiona el evento de cuando ponemos el dedo sobre la pantalla. Casi todos los eventos
     * procesan este evento en primer lugar, asi que no puede ser bloqueado
     * */
    fun onDown(event: MotionEvent): Boolean

    /** Evento que se lanza cuando dejamos pulsado sobre la pantalla durante un tiempo largo */
    fun onLongPress(event: MotionEvent)

    /** Evento que se lanza cuando hacemos dos toques seguidos en la pantalla */
    fun onDoubleTap(event: MotionEvent): Boolean

    /**
     * TODO -- No se cual es la intencion de esta funcion pero necesitamos implementarla para
     * que funcione la herencia que luego hacemos
     * */
    fun onShowPress(event: MotionEvent)
}

/**
 * Clase para manejar los eventos generados por los gestos
 * @param navigationDirector para poder navegar a los distintos puntos de nuestra aplicacion
 *
 * Notar que tambien podemos gestionar los eventos a nivel de cada componente de Jetpack Compose
 *
 * TODO -- la interfaz recoge los metodos que tenemos que implementar por las clases abstractas
 * de las que heredamos
 * */
@RequiresApi(Build.VERSION_CODES.M)
class DefaultGestureManager(val navigationDirector: NavigationDirector):
    ComponentActivity(),
    GestureDetector.OnGestureListener,
    GestureDetector.OnDoubleTapListener,
    GestureDetector.OnContextClickListener,
    GestureManager
{

    /**
     * Define como gestionamos los eventos de dedos sobre la pantalla
     * Solo tenemos que gestionar los eventos que no gestionamos por otras funciones que
     * no sean onTouch
     *
     * @param event el evento que tenemos que procesar
     * */
    override fun onTouch(event: MotionEvent): Boolean {
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

    override fun onDown(event: MotionEvent): Boolean {
        // No hacemos nada con el evento, devolvemos true para no bloquear el resto de eventos
        // que usan este evento como partida
        return true
    }

    override fun onShowPress(event: MotionEvent) {
        // No hacemos nada
    }

    override fun onSingleTapUp(p0: MotionEvent?): Boolean {
        return true
    }

    override fun onScroll(p0: MotionEvent?, p1: MotionEvent?, p2: Float, p3: Float): Boolean {
        return true
    }

    /** Evento que se lanza cuando dejamos pulsado sobre la pantalla durante un tiempo largo */
    override fun onLongPress(event: MotionEvent) {
        // No hacemos nada de momento
    }

    override fun onFling(p0: MotionEvent?, p1: MotionEvent?, p2: Float, p3: Float): Boolean {
        return true
    }

    override fun onSingleTapConfirmed(p0: MotionEvent?): Boolean {
        return true
    }

    /** Evento que se lanza cuando hacemos dos toques seguidos en la pantalla */
    override fun onDoubleTap(event: MotionEvent): Boolean {
        this.navigationDirector.navigate(NavigationMapper.FOCUS_MODE_SESSION)
        return true
    }

    override fun onDoubleTapEvent(p0: MotionEvent?): Boolean {
        return true
    }

    override fun onContextClick(p0: MotionEvent?): Boolean {
        return true
    }
}