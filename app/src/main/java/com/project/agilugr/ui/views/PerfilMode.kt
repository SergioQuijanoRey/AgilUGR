package com.project.agilugr.ui.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
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
        Column(

            // Lo espaciamos algo respecto el extremo superior del telefono y respecto el borde izquierdo
            modifier = Modifier
                .padding(vertical = 0.dp, horizontal = 20.dp),
        ) {
            HeaderForProfile(
                backgroundColor = MaterialTheme.colors.primary,
                textColor = MaterialTheme.colors.onPrimary,
            ).getComponent()

            PradoButton()

            CorreoButton()

            // Todo Icons pannel
        }

    }

}