package com.project.agilugr.ui.views
import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
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
import androidx.compose.ui.viewinterop.AndroidView
import com.project.agilugr.utils.MainBackground
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Con esta clase creamos la vista del Chatbot de los eventos
 */
class EventBot (){

    /** Devuelve la vista que representa esta clase */
    @Composable
    fun getView() {
        //Misma función del fichero Academibot.kt,pero le pasamos la nueva URL
        WebPageScreen("https://console.dialogflow.com/api-client/demo/embedded/ddf811ab-1d1a-489d-8ea1-a3c38ecf08d1")

    }

}
