package com.project.agilugr.ui.views


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.project.agilugr.R
import com.project.agilugr.ui.components.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.draw.rotate


/**
 * Vista en la que se muestra la TUI
 * Esta vista de la TUI se va a usar para realizar pagos facilmente y otras gestiones
 * */
class TuiView (
    /** Para poder controlar la navegacion a otras partes de la app */
    val navController: NavController
){

    /** Devuelve la vista que representa esta clase */
    @Composable
    fun getView() {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            HeaderForProfile().getComponent()
            Spacer(modifier = Modifier.height(30.dp))

            Image(painter = painterResource(id = R.drawable.reversotui), contentDescription = "Logo", modifier = Modifier
                .rotate(90F)
                .size(800.dp)
                .clip(
                    RoundedCornerShape(10.dp)
                ) )

        }

    }

}
