package com.project.agilugr.ui.navigation

/** Enumerado para no tener que hardcodear todas las vistas con sus rutas en la base del codigo */
enum class NavigationMapper(val route: String){
    FOCUS_MODE_SELECTOR(route = "focus_mode_selector_view"),
    FOCUS_MODE_SESSION(route = "focus_mode_session_view"),
    MAIN_VIEW(route="main_view"),
    PERFIL_MODE(route="perfil_view")
}