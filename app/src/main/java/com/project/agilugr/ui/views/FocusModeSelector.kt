package com.project.agilugr.ui.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.project.agilugr.FocusAPI
import com.project.agilugr.ui.components.CardComponent
import com.project.agilugr.ui.components.Header
import com.project.agilugr.ui.navigation.NavigationDirector
import com.project.agilugr.ui.navigation.NavigationMapper
import com.project.agilugr.utils.phone_dimensions

/** Clase que representa la vista de la pantalla desde la que, seleccionamos una configuracion
 * concreta de Focus Mode y entramos en una sesion de Focus Mode*/
class FocusModeSelector(val focus_api: FocusAPI, val navController: NavController) {

    @Composable
    fun getView(){
        Column(

            // Lo espaciamos algo respecto el extremo superior del telefono y respecto el borde izquierdo
            modifier = Modifier
                .padding(vertical = 10.dp, horizontal = 20.dp),
        ) {

            // Barra vertical que mostramos en toda la app
            Header(
                backgroundColor = MaterialTheme.colors.primary,
                textColor = MaterialTheme.colors.onPrimary,
            ).getComponent()

            // Titulo de esta vista
            // TODO -- igual deberiamos sustituirlo por la barra vertical
            Text(
                "Selector de config Focus Session",
                color = MaterialTheme.colors.onBackground,
                style = MaterialTheme.typography.h5,
            )
            Spacer(modifier = Modifier.size(width = 0.dp, height = 15.dp))

            // Cards con las distintas configuraciones
            focus_api.getAllConfigs().forEach{ config ->

                // Añadimos la card
                CardComponent(
                    text = config.toString(),

                    backgroundColor = MaterialTheme.colors.primary,
                    textColor = MaterialTheme.colors.onPrimary,
                    circleColor = MaterialTheme.colors.secondary,

                    // TODO -- no estamos usando la configuracion correcta
                    // Lo ideal seria que tomasemos la configuracion que estamos usando, la pasemos por
                    // el navegador e iniciasemos la session con la configuracion dada
                    onClick = { navController.navigate(NavigationMapper.FOCUS_MODE_SESSION.route) }
                ).getComponent()

                // Añadimos algo de espaciado vertical con la siguiente card
                Spacer(modifier = Modifier.size(width = 0.dp, height = 15.dp))
            }
        }
    }
}