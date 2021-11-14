/** Modulo en el que definimos el tema que vamos a usasr en nuestra aplicacion*/
package com.project.agilugr.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/** Definimos los colores que vamos a usar en el tema oscuro */
private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200,
    background = SoftGray,

    // Color de letras que se muestran encima de un background
    onBackground = Color.White,

    // Color qde letras que se muestran encima de un background con color primario
    onPrimary = Color.White,
)

/** Definimos los colores que vamos a usar en el tema claro */
private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200
)

/**
 * Tema que vamos a usar en nuestra aplicacion
 * Usamos las paletas de colores anteriormente definidas para ello
 * */
@Composable
fun AgilUGRTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}