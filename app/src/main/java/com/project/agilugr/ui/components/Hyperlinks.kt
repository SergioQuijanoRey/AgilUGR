package com.project.agilugr.ui.components


import android.content.Intent
import android.net.Uri
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.material.Text
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.project.agilugr.ui.navigation.NavigationMapper
import androidx.navigation.NavController


/**
 * Boton que nos va a llevar a la pagina de PRADO para acceder a contenido de la universidad
 * */
@Composable
fun PradoButton() {
    val context = LocalContext.current
    val intent = remember { Intent(Intent.ACTION_VIEW, Uri.parse("https://pradogrado2122.ugr.es/")) }
    Button(onClick = { context.startActivity(intent) }) {
        Text(text = "Prado 21-22")
    }
}

/**
 * Boton que nos va a llevar a la pagina del correo de la universidad
 * */
@Composable
fun CorreoButton() {
    val context = LocalContext.current
    val intent = remember { Intent(Intent.ACTION_VIEW, Uri.parse("https://webmailest.ugr.es/")) }
    Button(onClick = { context.startActivity(intent) }) {
        Text(text = "Correo electrónico")
    }
}

/**
 * Boton que nos sirve para navegar hacia la ruta del perfil, para poder navegar usando botones
 * y no usando obligatoriamente los gestos
 * */
@Composable
fun NavButton(navController:NavController ){
    Button(onClick = { navController.navigate(NavigationMapper.PERFIL_MODE.route) }) {

    }
}