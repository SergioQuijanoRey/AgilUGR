package com.project.agilugr.ui.views

import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.project.agilugr.FocusAPI
import com.project.agilugr.backend.IndexAPI
import com.project.agilugr.ui.components.CardComponent
import com.project.agilugr.ui.components.Header
import com.project.agilugr.ui.navigation.NavigationDirector
import com.project.agilugr.ui.navigation.NavigationMapper
import com.project.agilugr.utils.phone_dimensions
import kotlin.random.Random

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
            Column() {
                Header(
                    backgroundColor = MaterialTheme.colors.primary,
                    textColor = MaterialTheme.colors.onPrimary,
                ).getComponent()

                Spacer(modifier = Modifier.size(width = 0.dp, height = 7.dp))
                // Titulo de esta vista
                // TODO -- igual deberiamos sustituirlo por la barra vertical
                Text(
                    "Selector Sesión Focus",
                    color = MaterialTheme.colors.onBackground,
                    style = MaterialTheme.typography.h5,
                )
            }

            Spacer(modifier = Modifier.size(width = 0.dp, height = 15.dp))


            //Llamamos a la función que genera las tarjetas de horarios
            Cards(navController, focus_api)

            // Cards con las distintas configuraciones
            /*focus_api.getAllConfigs().forEach{ config ->

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
            }*/
            GenerarEvento()
        }
    }
}


//Función que genera las tarjetas con el scroller
@Composable
fun Cards(navController: NavController, focus_api: FocusAPI)
{
    val scrollState: ScrollState = rememberScrollState()

    Box(
        modifier = Modifier.size(400.dp,370.dp)
    ) {
        Column(modifier = Modifier.verticalScroll(scrollState)) {
            focus_api.getAllConfigs().forEach{ config ->

                // Añadimos la card
                CardComponent(
                    //Text(text = config.toString(), fontSize = 20.sp),

                    text = config.toString(),

                    backgroundColor = Color.DarkGray,
                    textColor = Color.White,
                    circleColor = Color.LightGray,

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


@Composable
fun GenerarEvento (){


        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally)
        {
                Button(
                    onClick = { },
                    modifier = Modifier
                        .padding(8.dp)
                        .size(170.dp, 120.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Text(text = "Nuevo modo", fontSize = 20.sp, color = Color.White, fontWeight = FontWeight.Bold)
                }
            Spacer(modifier = Modifier.size(width = 0.dp, height = 15.dp))

        }
}
