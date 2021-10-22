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
            "Tomorrow you have your NPI project defense",
            "But today you have a lovely day <3"
        )
        return output
    }
    companion object {
        @kotlin.time.ExperimentalTime
        fun getMockIndexAPI (): IndexAPI {
            return MockedProfile()
        }
    }
}




