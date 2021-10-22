package com.project.agilugr.ui.views

import com.project.agilugr.ui.components.AlertBox

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.project.agilugr.FocusAPI
import com.project.agilugr.backend.IndexAPI
import com.project.agilugr.ui.components.CardComponent
import com.project.agilugr.ui.navigation.NavigationDirector
import com.project.agilugr.ui.navigation.NavigationMapper
import com.project.agilugr.utils.phone_dimensions

import com.project.agilugr.ui.components.Header
import com.project.agilugr.ui.components.NavButton

/**
 * Esta clase representa la vista printipal en la que se selecciona
 * la funcionalidad
 *
 */
class IndexSelector (val indexApi : IndexAPI, val navController: NavController){
    @Composable
    fun getView() {
        Column(

            // Lo espaciamos algo respecto el extremo superior del telefono y respecto el borde izquierdo
            modifier = Modifier
                .padding(vertical = 0.dp, horizontal = 20.dp),
        ) {
            Header(
                backgroundColor = MaterialTheme.colors.primary,
                textColor = MaterialTheme.colors.onPrimary,
            ).getComponent()

            AlertBox(
                alerts = indexApi.getAlert() ,
                cardBackgroundColor = Color.Red,
                textColor = Color.White
            ).getComponent()

            NavButton(navController = navController)
        }

    }

}