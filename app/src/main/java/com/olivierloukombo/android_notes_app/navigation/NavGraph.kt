package com.olivierloukombo.android_notes_app.navigation

import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.olivierloukombo.android_notes_app.views.DetailNote
import com.olivierloukombo.android_notes_app.views.ListNote
import com.olivierloukombo.android_notes_app.views.SplashScreen
import com.olivierloukombo.android_notes_app.vm.DetailNoteViewModel
import com.olivierloukombo.android_notes_app.vm.ListNoteViewModel


@Composable
fun SetupNavGraph(navController: NavHostController, viewModel: ViewModel) {

    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route
    ) {
        composable(route = Screen.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
        composable(
            route = Screen.ListNoteScreen.route
        ) {
            val listNoteViewModel: ListNoteViewModel = hiltViewModel()
            ListNote(navController = navController, listNoteViewModel)
        }
        composable(route = Screen.AddNoteScreen.route +
                "?noteId={noteId}",
            arguments = listOf(
                navArgument(
                    name = "noteId"
                ) {
                    type = NavType.IntType
                    defaultValue = -1

                })
        ) {

            val detailNoteViewModel: DetailNoteViewModel = hiltViewModel()
            DetailNote(navController = navController, detailNoteViewModel)
        }
    }

}