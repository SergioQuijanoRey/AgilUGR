package com.project.agilugr.ui.navigation

/**
 * Enumerado para no tener que hardcodear todas las vistas con sus rutas en la base del codigo
 *
 * Gracias a este enumerado evitamos errores al escribir el string que representa las rutas de las vistas
 *
 * Ademas, en [NavigationDirector] solo podemos usar este enumerado para la navegacion, forzando de
 * esta forma evitar los fallos anteriormente descritos
 * */
enum class NavigationMapper(val route: String){
    FOCUS_MODE_SELECTOR(route = "focus_mode_selector_view"),
    FOCUS_MODE_SESSION(route = "focus_mode_session_view"),
    MAIN_VIEW(route="main_view"),
    PERFIL_MODE(route="perfil_view"),
    CALENDAR(route="calendar_view"),
    TUI_VIEW(route = "tui_view"),
    STATS(route = "stats_view")

}

/**
 * Dado un string representando una ruta en la APP, devuelve el valor del enumerado asociado
 * Levanta una excepcion cuando no existe ningun valor del enumerado asociado a la ruta pasada
 * */
fun stringToEnum(route: String): NavigationMapper{

    // Hacemos una busqueda lineal sobre los distintos valores del enumerado
    for(enum_value in NavigationMapper.values()){

        // Hemos encontrado el valor dentro del enumerado buscado
        // Devolvemos dicho enumerado
        if(enum_value.route == route){
            return enum_value
        }
    }

    // No hemos pasado el valor esperado, lanzamos una excepcion
    throw Exception("La ruta " + route + " no es valida")
}