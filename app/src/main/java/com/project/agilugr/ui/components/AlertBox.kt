package com.project.agilugr.ui.components

import androidx.compose.animation.expandVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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

class AlertBox (
    val alerts : List<String>,
    /** Colores que vamos a usar en la card */
    val cardBackgroundColor: Color,
    val textColor: Color,

    ) : ComponentAPI{

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
