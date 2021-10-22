package com.project.agilugr.constants

// TODO -- por que esta clase se llama MainView y no tiene un nombre que se refiera a que simplemente
// almacena constantes?
/**
 * Variables constantes usadas en la app:
 *  - Nombre de la app
 *  - perfil de usuario
 *  - email del usuario
 *  - Carrera del usuario
 */
enum class MainView (val text: String){
    AppTitle("Agil UGR"),
    ProfileName("Euler Sanchez"),
    ProfileMail("eulersanchez@ugr.es"),
    Degree("Beautiful Art")
}