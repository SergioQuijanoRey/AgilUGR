package com.project.agilugr.ui.views

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.project.agilugr.R
import com.project.agilugr.backend.PerfilAPI
import com.project.agilugr.ui.components.*
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.roundToInt
/**
 * Esta clase representa la vista printipal en la que se selecciona
 * la funcionalidad
 *
 */
class PerfilMode (val perfilApi : PerfilAPI, val navController: NavController){
    @Composable
    fun getView() {
        HeaderForProfile(
            backgroundColor = MaterialTheme.colors.primary,
            textColor = MaterialTheme.colors.onPrimary,
        ).getComponent()

        Column(
            // Lo espaciamos algo respecto el extremo superior del telefono y respecto el borde izquierdo
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            TuiCard()
        }

        Column(
            // Lo espaciamos algo respecto el extremo superior del telefono y respecto el borde izquierdo
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row() {
                Column() {
                    IconMail()
                }

                Column() {
                    IconPrado()
                }
            }
        }



    }

}

@Composable
fun Tui ( ) {
    return (
            Image(
                painter = painterResource(id = R.drawable.tui),
                contentDescription = "Tui Picture",
                modifier = Modifier
                    .clip(RectangleShape)
                    .fillMaxSize()
            )
            )
}


@Composable
fun IconMail() {
    val context = LocalContext.current
    val intent = remember { Intent(Intent.ACTION_VIEW, Uri.parse("https://webmailest.ugr.es/")) }
    IconButton(modifier = Modifier
        .padding(50.dp)
        .size(100.dp), onClick = { context.startActivity(intent) }) {
        Column() {
            //Icon(
            //    Icons.Filled.Email, contentDescription = "Localized description",
            //    Modifier.size(80.dp)
            //)
            
            Image(painter = painterResource(id = R.drawable.correo1), contentDescription ="Correo")
        }

    }
}

@Composable
fun IconPrado() {
    val context = LocalContext.current
    val intent = remember { Intent(Intent.ACTION_VIEW, Uri.parse("https://pradogrado2122.ugr.es/")) }
    IconButton(modifier = Modifier
        .padding(50.dp)
        .size(100.dp), onClick = { context.startActivity(intent) }) {
        Column() {
            Image(painter = painterResource(id = R.drawable.prado), contentDescription ="Correo")
        }

    }
}


/////////////// Lo que viene ahora hay que entenderlo bien




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