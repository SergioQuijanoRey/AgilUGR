package com.project.agilugr.ui.views


import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
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
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.project.agilugr.utils.MainBackground
import com.project.agilugr.utils.headerBackground


/**
 * Esta clase representa la vista de las estadísticas
 *
 */
class StatsView (val navController: NavController, var fusedLocationClient: FusedLocationProviderClient){
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

                var pos_got_ok = false
                var got_location: Location? = null
                var got_location_lat = 0.0
                var got_location_long = 0.0
                if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {

                    // Tomamos de google maps la posicion de la ETSIIT
                    pos_got_ok = false
                    got_location_lat = 37.197360
                    got_location_long = -3.624644
                }
                fusedLocationClient.lastLocation
                    .addOnSuccessListener { location : Location? ->
                        pos_got_ok = true
                        got_location = location!!
                        /* got_location_lat = got_location.getLatitude() */
                        /* got_location_long = got_location.getLongitude() */
                    }

                if(pos_got_ok == false){
                    Text("Se obtuvo bien la posicion GPS")
                    Text("Localizacion: lat ${got_location_lat}, long ${got_location_long}")
                }else{
                    Text("Se obtuvo bien la posicion GPS")
                    Text("Localizacion: lat ${got_location!!.getLatitude()}, long ${got_location!!.getLongitude()}")
                }
            }
        }

    }
}
