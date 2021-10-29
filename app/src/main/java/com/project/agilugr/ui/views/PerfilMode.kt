package com.project.agilugr.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.project.agilugr.R
import com.project.agilugr.backend.PerfilAPI
import com.project.agilugr.ui.components.*

/**
 * Esta clase representa la vista printipal en la que se selecciona
 * la funcionalidad
 *
 */
class PerfilMode (val perfilApi : PerfilAPI, val navController: NavController){
    @Composable
    fun getView() {
        HeaderForProfile(
            backgroundColor = MaterialTheme.colors.primary,
            textColor = MaterialTheme.colors.onPrimary,
        ).getComponent()

        Column(
            // Lo espaciamos algo respecto el extremo superior del telefono y respecto el borde izquierdo
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Tui()
        }

        Column(
            // Lo espaciamos algo respecto el extremo superior del telefono y respecto el borde izquierdo
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row() {
                PradoButton()
                CorreoButton()
            }
        }



    }

}

@Composable
fun Tui ( ) {
    return (
            Image(
                painter = painterResource(id = R.drawable.tui),
                contentDescription = "Profile picture",
                modifier = Modifier
                    .clip(RectangleShape)
                    .fillMaxSize()
            )
            )
}