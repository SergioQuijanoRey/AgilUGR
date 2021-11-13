package com.project.agilugr.ui.components

import androidx.compose.runtime.Composable

interface ComponentAPI {

    /** Funcion que debe implementar un componente para tomar la UI de dicho componente*/
    @Composable
    fun getComponent()
}