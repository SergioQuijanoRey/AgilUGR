package com.project.agilugr.ui.navigation

import org.junit.Assert.*

import org.junit.Test
import java.lang.Exception

class NavigationMapperTest {

    /** Comprobamos que todos los valores del enumerado son mappeados correctamente */
    @Test
    fun test_string_to_enum_all_values() {
        for(value in NavigationMapper.values()){
            assertTrue("No se mappea bien la ruta al enumerado", value == stringToEnum(value.route))
        }
    }

    /** Comprobamos que se lanza una excepcion para valores que no tengan sentido*/
    @Test
    fun test_string_to_enum_fails_in_some_cases(){
        var badRoute = "route_no_valida"

        val exception = assertThrows(Exception::class.java) {
            val enumValue = stringToEnum(badRoute)
        }
        assertEquals("La ruta $badRoute no es valida", exception.message)

    }
}