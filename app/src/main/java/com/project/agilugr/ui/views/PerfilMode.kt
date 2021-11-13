package com.project.agilugr.ui.views

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.IconButton
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.project.agilugr.R
import com.project.agilugr.ui.components.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import com.project.agilugr.utils.MainBackground
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Esta clase representa la vista printipal en la que se selecciona
 * la funcionalidad
 *
 */
class PerfilMode (){
    @Composable
    fun getView() {

        Box(modifier=Modifier.background(Color(MainBackground))){
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                HeaderForProfile().getComponent()
                Spacer(modifier = Modifier.height(150.dp))

                Box(modifier= Modifier
                    .background(Color.White)
                    .size(350.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(40.dp))){
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(painter = painterResource(id = R.drawable.logo), contentDescription = "Logo", modifier = Modifier
                            .size(150.dp))
                        Spacer(modifier = Modifier.height(5.dp))
                        TuiCard()
                        Spacer(modifier = Modifier.height(50.dp))
                    }


                }
                Spacer(modifier = Modifier.height(150.dp))

                Box(modifier = Modifier.background(Color(0xFFC4D8E7)) .fillMaxWidth()){
                    Row(modifier=Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                        IconMail()
                        IconPrado()
                        IconAccesoIdentificado()
                        IconComedor()
                    }
                }

            }

        }


    }

}

@Composable
fun IconMail() {
    val context = LocalContext.current
    val intent = remember { Intent(Intent.ACTION_VIEW, Uri.parse("https://webmailest.ugr.es/")) }
    IconButton(modifier = Modifier
        .size(100.dp), onClick = { context.startActivity(intent) }) {
        Column() {
            Image(painter = painterResource(id = R.drawable.round_email_black_48dp), contentDescription ="Correo")
        }

    }
}

@Composable
fun IconPrado() {
    val context = LocalContext.current
    val intent = remember { Intent(Intent.ACTION_VIEW, Uri.parse("https://pradogrado2122.ugr.es/")) }
    IconButton(modifier = Modifier
        .size(100.dp), onClick = { context.startActivity(intent) }) {
        Column() {
            Image(painter = painterResource(id = R.drawable.round_local_florist_black_48dp), contentDescription ="Prado")
        }

    }
}

@Composable
fun IconAccesoIdentificado() {
    val context = LocalContext.current
    val intent = remember { Intent(Intent.ACTION_VIEW, Uri.parse("https://oficinavirtual.ugr.es/ai/")) }
    IconButton(modifier = Modifier
        .size(100.dp), onClick = { context.startActivity(intent) }) {
        Column() {
            Image(painter = painterResource(id = R.drawable.round_alternate_email_black_48dp), contentDescription ="Acceso Identificado")
        }

    }
}

@Composable
fun IconComedor() {
    val context = LocalContext.current
    val intent = remember { Intent(Intent.ACTION_VIEW, Uri.parse("http://scu.ugr.es/#__doku_menu_comedor\ns")) }
    IconButton(modifier = Modifier
        .size(100.dp), onClick = { context.startActivity(intent) }) {
        Column() {
            Image(painter = painterResource(id = R.drawable.round_restaurant_black_48dp), contentDescription ="Acceso Identificado")
        }

    }
}

@Composable
fun TuiCard(
    advance: ()-> Unit = {},
){
    val coroutineScope = rememberCoroutineScope()
    val images = remember {
        mutableStateListOf(
            R.drawable.tui,
            R.drawable.reversotui,

            )
    }
    Box(
        modifier = Modifier.size(400.dp,200.dp)
    ) {
        images.take(10).reversed().forEach {
            key(it) {
                Card(id = it) {
                    images.add(
                        images.removeFirst()
                    )
                }
            }
        }
    }
}

@Composable
fun Card(
    id: Int,
    advance: ()-> Unit = {},
){
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .clickable {
                coroutineScope.launch {
                    delay(300)
                    advance()
                }
            }
    ) {
        Image(
            painter = painterResource(
                id = id,
            ),
            contentDescription = null,
            modifier = Modifier
                .size(400.dp, 400.dp)
        )
    }
}