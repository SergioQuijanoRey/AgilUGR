package com.project.agilugr.backend

import com.project.agilugr.FocusAPI
import com.project.agilugr.FocusConfig
import com.project.agilugr.FocusSession
import com.project.agilugr.utils.SessionDuration
import java.time.temporal.ChronoUnit

/**
 * Interfaz que deben implementar las clases que trabajen con las APIs de PerfilMode
 * */

interface PerfilAPI{


    /** Devuelve el Nombre completo del usuario */
    fun getNombreUsuario(): String?

    /**  Devuelve la carrera del usuario */
    fun getCarreraUsuario(): String?

    /**  Devuelve el correo del usuario */
    fun getCorreo(): String?
}

/**
 * Clase PerfilUsuario
 *
 * */
class PerfilUsuario (
    /** Nombre usuario */
    var nombre: String = "Nombre",
    var carrera: String = "Carrera",
    var correo: String = "@correo.ugr.es"

    )

/**
 * CLase que implementa la interfaz para ser una API de Perfil mode
 * */
class MockPerfilAPI(
    var usuario: PerfilUsuario
): PerfilAPI {

    override fun getNombreUsuario(): String {
        return "Nombre: ${usuario.nombre}"
    }

    override fun getCarreraUsuario(): String{
        return "Nombre: ${usuario.carrera}"
    }

    override fun getCorreo(): String{
        return "Nombre: ${usuario.correo}"
    }

    companion object{
        /** Devuelve una MockPerfilAPI ya instanciada correctamente */
        @kotlin.time.ExperimentalTime
        fun getMockPerfilAPI(): MockPerfilAPI{

            // Hardcodeamos las distintas configuraciones que tiene almacenado el usuario
            var perfil= PerfilUsuario(nombre="Jose Martinez Salas", carrera="Ingeniería Informática", correo="jmsalas@correo.ugr.es")

            // Generamos la instancia mock
            var mock = MockPerfilAPI(usuario = perfil)
            return mock
        }
    }
}

