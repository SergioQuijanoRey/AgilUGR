package com.project.agilugr.backend

import android.os.Build
import androidx.annotation.RequiresApi
import com.project.agilugr.utils.SessionDuration
import java.time.LocalTime
import java.time.temporal.ChronoUnit


/**
 * Interfaz que deben implementar las clases que trabajen con las APIs de Index
 * */
interface IndexAPI{

    /** Devuelve el conjunto de alertas actuales */
    fun getAlert(): List<String>

}

/**
 * Clase que representa una configuracion del índice
 * */
class MockedProfile() : IndexAPI{

    /** Para que se muestren mejor cuando lo pasamos a string */
    override fun getAlert(): List<String> {
        val output= listOf<String>(
            "Entrega de NPI",
            "Examen de Análisis Matemático II",
            "Entrega de PL",
            "Examen de Estadística Multivariante",
            "Exposición del trabajo de FR",
            "Examen de Modelos Matemáticos II"
        )
        return output
    }

    /**
     * Companion object que nos permite tener un metodo estatico para generar facilmente una instancia
     * del mock que poder consumir en otras partes con los datos ya rellenados
     * */
    companion object {
        @kotlin.time.ExperimentalTime
        fun getMockIndexAPI (): IndexAPI {
            return MockedProfile()
        }
    }
}





