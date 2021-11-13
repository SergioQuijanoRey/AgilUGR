package com.project.agilugr.ui.components
import com.project.agilugr.constants.ConstantsRepo

import androidx.compose.foundation.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**  Funciones para poder trabajar con imágenes */
import com.project.agilugr.R
import com.project.agilugr.utils.headerBackground

// TODO -- estaria bien poder incluir titulos para cada seccion
/** Componente para mostrar la cabecera de la App*/
class Header(
): ComponentAPI {

    @Composable
    override fun getComponent(){
        Row(
            modifier = Modifier.padding(all = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            AppIcon()
            Spacer(modifier = Modifier.width( 8.dp))
            Text( text = ConstantsRepo.AppTitle.text,
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                color = Color.White)
            Spacer(modifier = Modifier.width( 55.dp))
            ProfileIcon()
            Spacer(modifier = Modifier.width( 8.dp))
            Text(text = ConstantsRepo.ProfileName.text,color = Color.White)
        }
    }
}

/** Componente para mostrar un header concreto para la vista del perfil*/
class HeaderForProfile(
): ComponentAPI {

    @Composable
    override fun getComponent(){

        Box {
            // This box works as background
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(
                        Color(headerBackground),
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
                    AppIcon()
                    Spacer(modifier = Modifier.width( 8.dp))
                    Text( text = ConstantsRepo.AppTitle.text,
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp,
                        color = Color.White)
                    Spacer(modifier = Modifier.width( 40.dp))
                    ProfileIcon()
                    Spacer(modifier = Modifier.width( 8.dp))
                    Column (
                    ){
                        Text(text = ConstantsRepo.ProfileName.text,
                            fontSize = 15.sp,
                            color = Color.White)
                        Text(text = ConstantsRepo.ProfileMail.text,
                            fontSize = 15.sp,
                            color = Color.White)
                        Text(text = ConstantsRepo.Degree.text,
                            fontSize = 15.sp,
                            color = Color.White)
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
                painter = painterResource(id = R.drawable.round_account_circle_white_48),
                contentDescription = "Profile picture"
            )

            )
}

@Composable
fun AppIcon ( ) {
    return (
            Image(
                painter = painterResource(id = R.drawable.round_rocket_launch_white_48),
                contentDescription = "Rocket picture"
            )

            )
}