package com.project.agilugr

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalTime
import java.time.temporal.ChronoUnit

/**
 * Interfaz que deben implementar las clases que trabajen con las APIs de FocusMode
 * */
interface FocusAPIInterface{

    /** Devuelve la sesion Focus Mode que esta corriendo actualmente */
    fun getRunningFocusMode(): FocusSession?

    /** Devuelve la configuracion actual del focus mode que estamos corriendo*/
    fun getRunningFocusConfig(): FocusConfig?

    /** Corre una sesion de Focus Mode */
    fun runFocusMode(config: FocusConfig)
}

/** Clase que representa la duracion de una sesion de focus*/
// TODO -- mover a una carpeta con utilidades
data class SessionDuration(val hours: Int, val minutes: Int, val seconds: Int)

/**
 * Clase que representa una configuracion del focus mode
 *
 * TODO -- no me gusta este @Experimental, asi que buscar una alternativa
 * */
class FocusConfig (
    /** Duracion de la sesion que queremos iniciar */
    val duration: SessionDuration
) {
}

/**
 * Clase que representa una sesion de Focus Mode
 *
 * @param focus_config configuracion del modo focus
 * */
class FocusSession(
    /** Configuracion que estamos usando para esta sesion */
    val focus_config: FocusConfig
    ){

    /**
     * Instante en el que se inicio la sesion de focus
     * Se toma automaticamente cuando creamos una instancia de la clase
     * */
    @RequiresApi(Build.VERSION_CODES.O)
    val start_time: LocalTime = LocalTime.now()

    /** Devuelve los minutos que llevamos dentro de esta sesion*/
    @RequiresApi(Build.VERSION_CODES.O)
    fun getRunningMinutes(): Int{
        val now_time = LocalTime.now()
        val running_minutes = ChronoUnit.MINUTES.between(now_time, start_time)
        return running_minutes.toInt()
    }
}

/**
 * CLase que implementa la interfaz para ser una API de focus mode
 * */
class MockFocusAPI(
    var focus_configs: List<FocusConfig> = emptyList(),
    var current_focus_session: FocusSession? = null
): FocusAPIInterface{

    override fun getRunningFocusMode(): FocusSession?{
        return current_focus_session
    }

    override fun getRunningFocusConfig(): FocusConfig?{
        return current_focus_session?.focus_config
    }

    /**
     * Corre una sesion de Focus Mode
     *
     * Si habia otra sesion corriendo, esta sesion se sobrescribe con la nueva que creamos
     * Asi que es aconsejable usar el metodo getRunningFocusMode para saber si tenemos ya
     * una sesion corriendo o no
     * */
    override fun runFocusMode(config: FocusConfig){
        // Creamos la nueva sesion y sobrescribimos el valor de la sesion corriendo actualmente
        current_focus_session = FocusSession(config)
    }

}

/** Devuelve una MockFocusAPI ya instanciada correctamente */
@kotlin.time.ExperimentalTime
fun getMockFocusAPI(): MockFocusAPI{
    var focus_session = FocusSession(
        focus_config = FocusConfig(
            duration = SessionDuration(hours = 0, minutes = 45, seconds = 0)
        )
    )
    var mock = MockFocusAPI(focus_configs = emptyList(), current_focus_session = focus_session)
    return mock
}
