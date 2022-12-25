package com.olivierloukombo.android_notes_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import com.olivierloukombo.android_notes_app.navigation.SetupNavGraph
import com.olivierloukombo.android_notes_app.ui.theme.AndroidnotesappTheme
import com.olivierloukombo.android_notes_app.vm.ListNoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel  by viewModels<ListNoteViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidnotesappTheme {
                val navController = rememberNavController()
                SetupNavGraph(navController = navController, viewModel)
            }
        }
    }
}
