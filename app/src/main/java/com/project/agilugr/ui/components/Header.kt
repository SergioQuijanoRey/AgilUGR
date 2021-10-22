package com.project.agilugr.ui.components
import com.project.agilugr.constants.MainView

import androidx.compose.foundation.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**  Funciones para poder trabajar con imágenes */
import com.project.agilugr.R
import com.project.agilugr.backend.PerfilAPI

/** Componente para mostrar la cabecera de la App*/
class Header(
    /** Colores que vamos a usar en el header */
    val backgroundColor: Color,
    val textColor: Color,
    /** Funcion que llamamos cuando hacemos click en el banner. Por defecto no hacemos nada */
    val onClick: () -> Unit = {}
): ComponentAPI {

    @Composable
    override fun getComponent(){
        Row(
            modifier = Modifier.padding(all = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            ProfileIcon()
            Spacer(modifier = Modifier.width( 8.dp))
            Column (
                horizontalAlignment = Alignment.CenterHorizontally
                    ){
                // TODO add to constants structure (I do not know which is the most suitable structure needed)
                Text( text = "Agil UGR",
                    fontWeight = FontWeight.Bold,
                fontSize = 30.sp)
                Text(text = "Euler Sanchez")
            }
        }
    }
}

// TODO -- el nombre no me queda muy claro. Por que se llama header2 si lo que hace es mostrar
// tarjetas en la vista del perfil
/** Componente para mostrar tarjetas en la vista de Perfil*/
class Header2(
    /** Colores que vamos a usar en el header */
    val backgroundColor: Color,
    val textColor: Color,
    /** Funcion que llamamos cuando hacemos click en el banner. Por defecto no hacemos nada */
    val onClick: () -> Unit = {}
): ComponentAPI {

    @Composable
    override fun getComponent(){
        Row(
            modifier = Modifier.padding(all = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            ProfileIcon()
            Spacer(modifier = Modifier.width( 8.dp))
            Column (
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                // TODO add to constants structure (I do not know which is the most suitable structure needed)
                Text( text = "Euler Sanchez",
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp)
                Text(text = "euler@correo.ugr.es")
                Text(text = "Ingeniería Informática")
            }
        }
    }
}

@Composable
fun ProfileIcon ( ) {
    return (
            Image(
                painter = painterResource(id = R.drawable.profile_icon),
                contentDescription = "Profile picture",
                modifier = Modifier
                    .clip(CircleShape)

            )
            )
}