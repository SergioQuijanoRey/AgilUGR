package com.project.agilugr.utils

import android.app.Activity
import android.util.DisplayMetrics
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import android.view.WindowManager

/** Clase para tomar las dimensiones del telefono */
class phone_dimensions {

    /** Devuelve la anchura del telefono en DPs */
    fun getWidthDP(): Dp {
        return Dp(value = 200.0F)
    }

    /** Devuelve la altura del telefono en DPs */
    fun getHeightDP(): Dp {
        return Dp(value = 400.0F)
    }

    /** Devuelve la mitad de la altura del telefono en DPs */
    fun getHalfHeightDP(): Dp {
        val fullHeight = this.getHeightDP()
        return fullHeight / 2

    }
}