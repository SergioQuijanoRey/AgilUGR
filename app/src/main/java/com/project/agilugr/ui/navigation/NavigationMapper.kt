package com.project.agilugr.ui.navigation

/** Enumerado para no tener que hardcodear todas las vistas con sus rutas en la base del codigo */
enum class NavigationMapper(val route: String){
    FOCUS_MODE_SELECTOR(route = "focus_mode_selector_view"),
    FOCUS_MODE_SESSION(route = "focus_mode_session_view"),
    MAIN_VIEW(route="main_view"),
    PERFIL_MODE(route="perfil_view")
}

// TODO -- Debe de haber alguna funcion de kotlin para hacer esto
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