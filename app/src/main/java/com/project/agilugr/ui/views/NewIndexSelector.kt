package com.project.agilugr.ui.views
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.project.agilugr.ui.navigation.NavigationMapper
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.project.agilugr.R
import com.project.agilugr.backend.IndexAPI
import com.project.agilugr.ui.components.Header
import com.project.agilugr.utils.MainBackground
import com.project.agilugr.utils.events
import com.project.agilugr.utils.headerBackground

import kotlin.random.Random

/**
 * Esta clase representa la vista principal a traves de la cual navegamos hacia otras vistas de
 * nuestras aplicaciones.
 */
class NewIndexSelector(){
    /** Devuelve la vista que representa esta clase */
    @Composable
    fun getView() {
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
                Alertas2(listOf<String>(
                    "Entrega de NPI",
                    "Examen de Análisis Matemático II",
                    "Entrega de PL",
                    "Examen de Estadística Multivariante",
                    "Exposición del trabajo de FR",
                    "Examen de Modelos Matemáticos II"
                ), Color(events), Color.Black, Color.White)
                Spacer(modifier = Modifier.size(50.dp))
                Box(modifier= Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(
                        Color.White
                    )){
                    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
                        PerfilIcon2()
                        Row() {
                            FocusIcon2()
                            Spacer(modifier = Modifier.size(30.dp))
                            CalendarIcon2()

                        }
                        StatsIcon2()

                    }

                }
            }


        }

    }

}

@Composable
        /** Función que crea el botón-imagen que direcciona al modo focus */
fun FocusIcon2() {
    IconButton(modifier = Modifier
        .padding(20.dp)
        .size(70.dp), onClick = {}
    ) {
        Column(){
            Image(painter = painterResource(id = R.drawable.round_tungsten_black_48dp), contentDescription ="IconoFocus",Modifier.size(55.dp))
        }
    }
}

@Composable
        /** Función que crea el botón-imagen que direcciona al calendario */
fun CalendarIcon2() {
    IconButton(modifier = Modifier
        .padding(20.dp)
        .size(70.dp), onClick = {}
    ) {
        Column(){
            Image(painter = painterResource(id = R.drawable.round_edit_calendar_black_48dp), contentDescription ="IconoCalendario",Modifier.size(50.dp))
        }
    }
}

@Composable
        /** Función que genera el botón-imagen que direcciona al perfil */
fun PerfilIcon2() {
    IconButton(modifier = Modifier
        .padding(20.dp)
        , onClick = {}
    ) {
        Column(){
            Image(painter = painterResource(id = R.drawable.round_school_black_48dp), contentDescription ="iconoPerfil",Modifier.size(55.dp))
        }
    }
}


@Composable
        /** Función que genera el botón-imagen que direcciona a las estadísticas */
fun StatsIcon2() {
    IconButton(modifier = Modifier
        .padding(20.dp)
        .size(70.dp), onClick = {}
    ) {
        Column(){
            Image(painter = painterResource(id = R.drawable.outline_smart_toy_black_48dp), contentDescription ="iconoStats",Modifier.size(55.dp))
        }
    }
}

/**
 * Función que pone las alertas y avisos en la parte superior de la pantalla.
 * Usa la API para tener los mensajes
 * */
@SuppressLint("ResourceType")
@Composable
fun Alertas2(alerts : List<String>,
              /** Colores que vamos a usar en la card */
              cardBackgroundColor: Color,
              textColor: Color,
              encabezadoColor: Color )
{
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
                        .fillMaxWidth()
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

class UIFragmentIndex() : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = ComposeView(requireContext())
        view.apply{
            setContent {
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
                        Alertas2(listOf<String>(
                            "Entrega de NPI",
                            "Examen de Análisis Matemático II",
                            "Entrega de PL",
                            "Examen de Estadística Multivariante",
                            "Exposición del trabajo de FR",
                            "Examen de Modelos Matemáticos II"
                        ), Color(events), Color.Black, Color.White)
                        Spacer(modifier = Modifier.size(5.dp))
                        Box(modifier= Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(
                                Color.White
                            )){
                            Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
                                IconButton(modifier = Modifier
                                    .padding(20.dp)
                                    , onClick = {findNavController().navigate(R.id.viewIndex)}
                                ) {
                                    Column(){
                                        Image(painter = painterResource(id = R.drawable.round_school_black_48dp), contentDescription ="iconoPerfil",Modifier.size(55.dp))
                                    }
                                }
                                Row() {
                                    FocusIcon2()
                                    Spacer(modifier = Modifier.size(30.dp))
                                    CalendarIcon2()

                                }
                                StatsIcon2()

                            }

                        }
                    }


                }

            }
        }
        return view
    }
}
