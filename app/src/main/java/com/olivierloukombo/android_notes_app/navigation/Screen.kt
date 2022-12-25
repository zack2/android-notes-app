package com.olivierloukombo.android_notes_app.navigation

sealed class Screen(val route: String){
    object SplashScreen : Screen("SplashScreen")
    object ListNoteScreen : Screen("ListNoteScreen")
    object AddNoteScreen: Screen("AddNoteScreen")
}
