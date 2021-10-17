package com.project.agilugr.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.interaction.FocusInteraction
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.project.agilugr.FocusAPI
import com.project.agilugr.MockFocusAPI
import com.project.agilugr.backend.IndexAPI
import com.project.agilugr.backend.MockedProfile
import com.project.agilugr.constants.MainView
import com.project.agilugr.ui.views.FocusModeSelector
import com.project.agilugr.ui.views.FocusModeSessionView
import com.project.agilugr.ui.views.IndexSelector
import kotlin.time.ExperimentalTime

/** Clase que maneja toda la navegacion de nuestra aplicacion */
class NavigationDirector(val focus_api: FocusAPI){

    var navController: NavController?
        get() {
            return navController
        }
        set(value) {navController = value}

    /**
     * Construye la navigacion para nuestra aplicacion
     *
     * Trabaja con un NavController y construye un NavHost compuesto de las distintas vistas de
     * nuestra aplicacion.
     *
     * Gracias al parametro startDestination, la funcion devuelve la vista desde la que parte
     * nuestra aplicacion, y con ello comienza la interfaz grafica de nuestra aplicacion
     * */
    @RequiresApi(Build.VERSION_CODES.O)
    @ExperimentalTime
    @Composable
    fun buildNavigationAndStartUI() {

        // El controlador que necesitamos para controlar en detalle la navegacion
        // No estamos entrando en detalle, pero modificando esta variable podemos acceder a ello
        this.navController = rememberNavController()

        // El NavHost define las vistas que disponemos y como navegamos entre ellas
        NavHost(

            // El controlador que vamos a usar para la navegacion
            navController = this.navController as NavHostController,

            // La vista inicial
            startDestination = NavigationMapper.FOCUS_MODE_SELECTOR.route
        ){
            // Vista principal, índice de selección de otras vistas
            composable(route = NavigationMapper.MAIN_VIEW.route) {
                // TODO add MockedProfile correctly
                IndexSelector( MockedProfile, navController = navController as NavHostController).getView()
            }
            // Vista del selector de configuraciones del focus mode
            composable(route = NavigationMapper.FOCUS_MODE_SELECTOR.route){
                FocusModeSelector(MockFocusAPI.getMockFocusAPI(), navController = navController as NavHostController).getView()
            }

            // Vista desde dentro de una sesion de focus mode
            composable(route = NavigationMapper.FOCUS_MODE_SESSION.route) {
                FocusModeSessionView(
                    MockFocusAPI.getMockFocusAPI(),
                    navController = navController as NavHostController
                ).getView()
            }
        }
    }

    /** Navega a una vista de destino dada */
    fun navigate(dest: NavigationMapper){
        this.navController!!.navigate(dest.route)
    }
}