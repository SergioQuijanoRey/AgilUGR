package com.project.agilugr.ui.navigation

import org.junit.Assert.*

import org.junit.Test

class NavigationMapperTest {

    /** Comprobamos que todos los valores del enumerado son mappeados correctamente */
    @Test
    fun test_string_to_enum_all_values() {
        for(value in NavigationMapper.values()){
            assertTrue("No se mappea bien la ruta al enumerado", value == stringToEnum(value.route))
        }
    }
}