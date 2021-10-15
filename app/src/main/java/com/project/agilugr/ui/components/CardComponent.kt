package com.project.agilugr.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.style.TextAlign

/** Componente para mostrar tarjetas*/
class CardComponent(
    /** Texto que mostramos en la card */
    val text: String,

    /** Colores que vamos a usar en la card */
    val backgroundColor: Color,
    val textColor: Color,
    val circleColor: Color,

    /** Funcion que llamamos cuando hacemos click en la card. Por defecto no hacemos nada */
    val onClick: () -> Unit = {}
): ComponentAPI {

    @Composable
    override fun getComponent(){

        Card(
            modifier = Modifier

                // Hacemos que sea clickable la card
                .clickable { onClick() }

                // TODO -- no esta funcionando
                // Bordes redondeados
                .border(brush = SolidColor(backgroundColor), width =  5.dp, shape = RoundedCornerShape(10.dp))

        ) {
            Row(
                modifier = Modifier
                    // Color del fondo
                    .background(color = backgroundColor)


            ){
                circle(circleColor)
                Spacer(modifier = Modifier.size(width = 15.dp, height = 0.dp))

                // TODO -- estoy centrando verticalmente el texto en la card de forma no optima
                Text(
                    text = text,
                    color = textColor,
                    modifier = Modifier.padding(horizontal = 0.dp, vertical = 10.dp)
                )
                Spacer(modifier = Modifier.size(width = 10.dp, height = 0.dp))
            }
        }
    }
}

@Composable
fun circle(circleColor: Color){
    Canvas(
        modifier = Modifier
            .size(45.dp)
            .padding(vertical = 5.dp),
        onDraw = {
        drawCircle(color = circleColor)
    })
}