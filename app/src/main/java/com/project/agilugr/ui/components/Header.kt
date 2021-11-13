package com.project.agilugr.ui.components
import com.project.agilugr.constants.ConstantsRepo

import androidx.compose.foundation.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**  Funciones para poder trabajar con imágenes */
import com.project.agilugr.R

// TODO -- estaria bien poder incluir titulos para cada seccion
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
                Text( text = ConstantsRepo.AppTitle.text,
                    fontWeight = FontWeight.Bold,
                fontSize = 30.sp)
                Text(text = ConstantsRepo.ProfileName.text)
            }
        }
    }
}

/** Componente para mostrar un header concreto para la vista del perfil*/
class HeaderForProfile(
    /** Colores que vamos a usar en el header */
    val backgroundColor: Color,
    val textColor: Color,
    /** Funcion que llamamos cuando hacemos click en el banner. Por defecto no hacemos nada */
    val onClick: () -> Unit = {}
): ComponentAPI {

    @Composable
    override fun getComponent(){

        Box {
            // This box works as background
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(
                        Color.LightGray,
                        // rounded corner to match with the OutlinedTextField
                        shape = RoundedCornerShape(4.dp)
                    )
                    .fillMaxWidth()
            )
                Row(
                    modifier = Modifier
                        .padding(all = 8.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ){
                    Spacer(modifier = Modifier.width( 8.dp))
                    ProfileIcon()
                    Spacer(modifier = Modifier.width( 8.dp))
                    Column (
                    ){
                        Text(text = ConstantsRepo.ProfileName.text)
                        Text(text = ConstantsRepo.ProfileMail.text)
                        Text(text = ConstantsRepo.Degree.text)
                    }
                    Spacer(modifier = Modifier.width( 50.dp))
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