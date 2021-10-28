package com.project.agilugr.ui.navigation

/** Enumerado para no tener que hardcodear todas las vistas con sus rutas en la base del codigo */
enum class NavigationMapper(val route: String){
    FOCUS_MODE_SELECTOR(route = "focus_mode_selector_view"),
    FOCUS_MODE_SESSION(route = "focus_mode_session_view"),
    MAIN_VIEW(route="main_view"),
    PERFIL_MODE(route="perfil_view");


}

// TODO -- Debe de haber alguna funcion de kotlin para hacer esto
/**
 * Dado un string representando una ruta en la APP, devuelve el valor del enumerado asociado
 * */
fun stringToEnum(route: String): NavigationMapper{
    if(route == "focus_mode_selector_view"){
        return NavigationMapper.FOCUS_MODE_SELECTOR
    }
    if(route == "focus_mode_session_view"){
        return NavigationMapper.FOCUS_MODE_SESSION
    }
    if(route == "main_view"){
        return NavigationMapper.MAIN_VIEW
    }
    if(route == "perfil_view"){
        return NavigationMapper.PERFIL_MODE
    }

    // No estamos en una vista correcta
    throw Exception("La ruta " + route + " no es valida")
}