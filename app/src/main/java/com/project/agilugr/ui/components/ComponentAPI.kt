package com.project.agilugr.ui.components

import androidx.compose.runtime.Composable

/**
 * Esta interfaz define las funciones que deben implementar las componentes para poder ser consideradas
 * como una componente. De esta forma unificamos lo que entiende todo el equipo de desarrollo como
 * una componente
 * */
interface ComponentAPI {

    /** Funcion que debe implementar un componente para tomar la UI de dicho componente*/
    @Composable
    fun getComponent()
}