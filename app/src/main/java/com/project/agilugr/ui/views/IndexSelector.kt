package com.project.agilugr.ui.views

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.project.agilugr.ui.navigation.NavigationMapper
import androidx.navigation.NavController
import com.project.agilugr.ui.components.AlertBox
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.project.agilugr.R
import com.project.agilugr.backend.IndexAPI
import com.project.agilugr.ui.components.ComponentAPI

import com.project.agilugr.ui.components.Header
import com.project.agilugr.ui.components.NavButton
import com.project.agilugr.ui.theme.SoftGray
import com.project.agilugr.utils.MainBackground
import kotlin.random.Random


/**
 * Esta clase representa la vista printipal en la que se selecciona
 * la funcionalidad
 *
 */
class IndexSelector (val indexApi : IndexAPI, val navController: NavController){
    @Composable
    fun getView() {

        Box(Modifier.background(Color.White)){
            Column(

                // Lo espaciamos algo respecto el extremo superior del telefono y respecto el borde izquierdo
                modifier = Modifier
                    .padding(vertical = 0.dp, horizontal = 20.dp),
            ) {
                Header(
                    backgroundColor = MaterialTheme.colors.primary,
                    textColor = MaterialTheme.colors.onPrimary,
                ).getComponent()


                Alertas(indexApi, indexApi.getAlert(), Color.DarkGray, Color.LightGray, Color.White)
                //ScrollableColumnDemo()


                /*AlertBox(
                    alerts = indexApi.getAlert() ,
                    cardBackgroundColor = Color.Red,
                    textColor = Color.White
                ).getComponent()*/


            }

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                PerfilIcon(navController = navController)
                Row() {
                    FocusIcon(navController = navController)
                    Spacer(modifier = Modifier.size(60.dp))
                    CalendarIcon(navController = navController)

                }
                //GPSIcon(navController = navController)

            }
        }

    }

}

@Composable
/* Función que crea el botón-imagen que direcciona al modo focus */

fun FocusIcon(navController:NavController) {
    IconButton(modifier = Modifier
        .padding(20.dp)
        .size(70.dp), onClick = { navController.navigate(NavigationMapper.FOCUS_MODE_SELECTOR.route)}
    ) {
        Column(){
            Image(painter = painterResource(id = R.drawable.iconofocus), contentDescription ="IconoFocus")
        }
    }
}

@Composable
/* Función que crea el botón-imagen que direcciona al calendario */
fun CalendarIcon(navController:NavController) {
    IconButton(modifier = Modifier
        .padding(20.dp)
        .size(70.dp), onClick = { navController.navigate(NavigationMapper.CALENDAR.route)}
    ) {
        Column(){
            Image(painter = painterResource(id = R.drawable.iconocalendario), contentDescription ="IconoCalendario")
        }
    }
}

@Composable
/* Función que genera el botón-imagen que direcciona al perfil*/
fun PerfilIcon(navController:NavController) {
    IconButton(modifier = Modifier
        .padding(20.dp)
        .size(70.dp), onClick = { navController.navigate(NavigationMapper.PERFIL_MODE.route)}
    ) {
        Column(){
            Image(painter = painterResource(id = R.drawable.iconoperfil), contentDescription ="iconoPerfil")
        }
    }
}


@Composable
/* Función que genera el botón-imagen que direcciona al perfil*/
fun GPSIcon(navController:NavController) {
    IconButton(modifier = Modifier
        .padding(20.dp)
        .size(70.dp), onClick = { navController.navigate(NavigationMapper.PERFIL_MODE.route)}
    ) {
        Column(){
            Image(painter = painterResource(id = R.drawable.iconogps), contentDescription ="iconoGPS")
        }
    }
}

/* Función que pone las alertas y avisos en la parte superior de la pantalla.
*   Usa la API para tener los mensajes*/
@Composable
fun Alertas ( indexApi : IndexAPI, alerts : List<String>,
             /** Colores que vamos a usar en la card */
             cardBackgroundColor: Color,
             textColor: Color,
             encabezadoColor: Color )
{
        val alerts = indexApi.getAlert()
        val scrollState: ScrollState = rememberScrollState()

        //Listas de nombre de tarea y fecha que asignaremos aleatoriamente
        val listTarea = listOf<String>("Examen", "Entrega")
        val listFecha = listOf<String>("17/11", "13/12", "3/12", "21/11", "16/12")

        Box(
            modifier = Modifier.size(400.dp,270.dp)
        ) {
            Column(modifier = Modifier.verticalScroll(scrollState)) {

                alerts.forEach {
                    Spacer(modifier = Modifier.height(0.dp))
                    androidx.compose.material.Card(
                        backgroundColor = cardBackgroundColor,
                        modifier = Modifier
                            /*.border(
                                brush = SolidColor(cardBackgroundColor),
                                width = 5.dp,
                                shape = RoundedCornerShape(10.dp)
                            )*/
                            .padding(horizontal = 10.dp, vertical = 10.dp)

                    ) {
                        //Redactamos el contenido de un bloque completo de texto
                        Column() {

                            //Situamos en la parte superior el nombre y fecha
                            Row() {

                                //Generamos los dos aleatorios
                                val randomIndex1 = Random.nextInt(listTarea.size)
                                val randomIndex2 = Random.nextInt(listFecha.size)

                                //Escribimos tarea
                                Text(
                                    text = listTarea[randomIndex1],
                                    color = encabezadoColor,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier
                                        .padding(horizontal = 10.dp, vertical = 10.dp)
                                )
                                //Escribimos texto
                                Text(
                                    text = listFecha[randomIndex2],
                                    color = encabezadoColor,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier
                                        .padding(horizontal = 10.dp, vertical = 10.dp)
                                )
                            }

                            //Escrbimos descripción de la tarea debajo
                            Text(
                                text = it,
                                color = textColor,
                                modifier = Modifier
                                    .padding(horizontal = 10.dp, vertical = 10.dp)
                            )
                        }

                        Spacer(modifier = Modifier.size(width = 5.dp, height = 5.dp))

                    }
                }
            }
        }
    }



