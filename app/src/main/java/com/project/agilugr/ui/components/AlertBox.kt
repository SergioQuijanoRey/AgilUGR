package com.project.agilugr.ui.components

import androidx.compose.animation.expandVertically
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.material.Text
import androidx.compose.material.Card
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import com.project.agilugr.backend.IndexAPI

/**
 * Componente que vamos a reusar en otras partes de la app
 *
 * Esta componente representa una caja con varias notificaciones (alertas)
 *
 * Ademas, podemos seleccionar distintos parametros que definen el comportamiento y aparencia del
 * componente
 * */
class AlertBox (

    /** Lista de alertas que queremos mostrar */
    val alerts : List<String>,

    /** Colores que vamos a usar en la card, como fondo */
    val cardBackgroundColor: Color,

    /** Colores que vamos a usar en la card, como color para el texto */
    val textColor: Color,

    ) : ComponentAPI{

    /**
     * Funcion que devuelve la vista de la componente
     * La vista en si es componible asi que puede ser reusada en otras vistas
     * */
    @Composable
    override fun getComponent() {

        Column {
           alerts.forEach {
               Spacer(modifier = Modifier.height(8.dp))
                Card(
                    backgroundColor= cardBackgroundColor,
                    modifier = Modifier
                        .border(
                            brush = SolidColor(cardBackgroundColor),
                            width = 5.dp,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(horizontal = 10.dp, vertical = 10.dp)

                ) {
                    Text(
                        text = it,
                        color = textColor,
                        modifier = Modifier
                            .padding(horizontal = 10.dp, vertical = 10.dp)
                    )
                    Spacer(modifier = Modifier.size(width = 10.dp, height = 10.dp))

                }
            }
       }
    }
}


