package com.project.agilugr.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.project.agilugr.FocusAPI
import com.project.agilugr.MockFocusAPI
import com.project.agilugr.backend.MockedProfile
import kotlin.time.ExperimentalTime
import com.google.accompanist.navigation.animation.composable
import com.project.agilugr.ui.views.*

/** Clase que maneja toda la navegacion de nuestra aplicacion */
class NavigationDirector(val focus_api: FocusAPI){

    /** Variable que vamos a usar para navegar por las distintas vistas */
    var navController: NavController? = null

    /**
     * Construye la navigacion para nuestra aplicacion
     *
     * Trabaja con un NavController y construye un NavHost compuesto de las distintas vistas de
     * nuestra aplicacion.
     *
     * Gracias al parametro startDestination, la funcion devuelve la vista desde la que parte
     * nuestra aplicacion, y con ello comienza la interfaz grafica de nuestra aplicacion
     * */
    @ExperimentalAnimationApi
    @RequiresApi(Build.VERSION_CODES.O)
    @ExperimentalTime
    @Composable
    fun buildNavigationAndStartUI(){

        // El controlador que necesitamos para controlar en detalle la navegacion
        // No estamos entrando en detalle, pero modificando esta variable podemos acceder a ello
        // Ademas, podemos usar este atributo para navegar a otras vistas
        this.navController = rememberAnimatedNavController()

        // El NavHost define las vistas que disponemos y como navegamos entre ellas
        AnimatedNavHost(

            // El controlador que vamos a usar para la navegacion
            navController = this.navController as NavHostController,

            // La vista inicial
            startDestination = NavigationMapper.MAIN_VIEW.route
        ){
            // Vista principal, índice de selección de otras vistas
            composable(route = NavigationMapper.MAIN_VIEW.route) {
                // TODO add MockedProfile correctly
                IndexSelector( MockedProfile.getMockIndexAPI(), navController = navController as NavHostController).getView()
            }

            // Vista del perfil
            composable(route = NavigationMapper.PERFIL_MODE.route) {
                // TODO add MockedProfile correctly
                PerfilMode().getView()
            }

            // Vista del selector de configuraciones del focus mode
            composable(
                route = NavigationMapper.FOCUS_MODE_SELECTOR.route,
                enterTransition = { _, _ ->
                    // Let's make for a really long fade in
                    fadeIn(animationSpec = tween(2000))
                }
            ){
                FocusModeSelector(MockFocusAPI.getMockFocusAPI(), navController = navController as NavHostController).getView()
            }

            // Vista desde dentro de una sesion de focus mode
            composable(route = NavigationMapper.FOCUS_MODE_SESSION.route){
                FocusModeSessionView(MockFocusAPI.getMockFocusAPI(), navController = navController as NavHostController).getView()
            }

            // Vista de la TUI
            composable(route = NavigationMapper.TUI_VIEW.route,
                enterTransition = { _, _ ->
                // Let's make for a really long fade in
                fadeIn(animationSpec = tween(2000))
            }){
                TuiView(navController = navController as NavHostController).getView()
            }

            composable(route = NavigationMapper.CALENDAR.route,
                    enterTransition = { _, _ ->
                        // Let's make for a really long fade in
                        fadeIn(animationSpec = tween(2000))
                    }){
                Calendario(navController = navController as NavHostController).getView()
            }
        }
    }

    /** Navega a un destino dado */
    fun navigate(destination: NavigationMapper){
        //Navegamos a esta vista
        this.navController!!.navigate(destination.route)

    }

    /** Tomamos el enumerado que representa la vista en la que nos encontramos actualmente */
    @JvmName("getCurrentView1")
    fun getCurrentView(): NavigationMapper {

        // Tomamos la ruta actual directamente desde el navController
        val current_route = this.navController!!.currentBackStackEntry!!.destination.route!!

        // Mappeamos dicha ruta a una de las vista que podemos interpretar
        return stringToEnum(current_route)
    }
}

