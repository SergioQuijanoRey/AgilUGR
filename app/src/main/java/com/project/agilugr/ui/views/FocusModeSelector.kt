package com.project.agilugr.ui.views

import androidx.compose.foundation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.project.agilugr.FocusAPI
import com.project.agilugr.ui.components.CardComponent
import com.project.agilugr.ui.components.Header
import com.project.agilugr.ui.navigation.NavigationMapper
import com.project.agilugr.utils.MainBackground
import com.project.agilugr.utils.events
import com.project.agilugr.utils.headerBackground

/** Clase que representa la vista de la pantalla desde la que, seleccionamos una configuracion
 * concreta de Focus Mode y entramos en una sesion de Focus Mode*/
class FocusModeSelector(val focus_api: FocusAPI, val navController: NavController) {

    @Composable
    fun getView(){

        Box(modifier= Modifier
            .background(Color(MainBackground))
            .fillMaxSize()){

            Box(modifier = Modifier
                .background(Color(headerBackground))
                .fillMaxWidth()
                .height(50.dp)
                .clip(
                    RoundedCornerShape(20.dp)
                )) {
                Column(

                    // Lo espaciamos algo respecto el extremo superior del telefono y respecto el borde izquierdo
                    modifier = Modifier
                        .padding(vertical = 0.dp, horizontal = 20.dp),
                ) {
                    Header().getComponent()

                }
            }
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(60.dp))
                // Titulo de esta vista
                Text(
                    "Selector para Sesión Focus",
                    color = MaterialTheme.colors.onBackground,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.h5,
                )
                Spacer(modifier = Modifier.size(width = 0.dp, height = 15.dp))

                //Llamamos a la función que genera las tarjetas de horarios
                Cards(navController, focus_api)

                // Cards con las distintas configuraciones
                GenerarEvento()
            }

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
        Column(modifier = Modifier.verticalScroll(scrollState) .fillMaxWidth()) {
            focus_api.getAllConfigs().forEach{ config ->

                // Añadimos la card
                CardComponent(
                    //Text(text = config.toString(), fontSize = 20.sp),
                    text = config.toString(),
                    backgroundColor = Color(events),
                    textColor = Color.Black,
                    circleColor = Color.White,
                    // Lo ideal seria que tomasemos la configuracion que estamos usando, la pasemos por
                    // el navegador e iniciasemos la session con la configuracion dada
                    onClick = { navController.navigate(NavigationMapper.FOCUS_MODE_SESSION.route) },
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
