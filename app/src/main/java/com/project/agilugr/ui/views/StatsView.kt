package com.project.agilugr.ui.views


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.project.agilugr.R
import com.project.agilugr.ui.components.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import com.project.agilugr.utils.MainBackground
import com.project.agilugr.utils.headerBackground


/**
 * Esta clase representa la vista de las estadísticas
 *
 */
class StatsView (val navController: NavController){
    @Composable
    fun getView() {
        Box(modifier= Modifier
            .background(Color(MainBackground))
            .fillMaxSize()) {

            Box(
                modifier = Modifier
                    .background(Color(headerBackground))
                    .fillMaxWidth()
                    .height(50.dp)
                    .clip(
                        RoundedCornerShape(20.dp)
                    )
            ) {
                Column(

                    // Lo espaciamos algo respecto el extremo superior del telefono y respecto el borde izquierdo
                    modifier = Modifier
                        .padding(vertical = 0.dp, horizontal = 20.dp),
                ) {
                    Header().getComponent()

                }
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Spacer(modifier = Modifier.height(70.dp))
                Text(text = "Hola chicos")

                /*
                Poner aquí los elementos de la vista












                 */

            }
        }

    }
}
