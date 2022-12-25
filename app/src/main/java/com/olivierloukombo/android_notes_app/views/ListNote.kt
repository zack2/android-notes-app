package com.olivierloukombo.android_notes_app.views

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.olivierloukombo.android_notes_app.component.NoteItem
import com.olivierloukombo.android_notes_app.component.Toolbar
import com.olivierloukombo.android_notes_app.data.model.Note
import com.olivierloukombo.android_notes_app.navigation.Screen
import com.olivierloukombo.android_notes_app.ui.theme.AndroidnotesappTheme
import com.olivierloukombo.android_notes_app.vm.ListNoteViewModel
import kotlinx.coroutines.flow.map

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "FlowOperatorInvokedInComposition")
@Composable
fun ListNote(navController: NavHostController, viewModel: ListNoteViewModel = hiltViewModel()) {
    val state = viewModel.state.value
    AndroidnotesappTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                Toolbar()
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        navController.navigate(Screen.AddNoteScreen.route)
                    },
                    backgroundColor = MaterialTheme.colors.primary
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add note")
                }
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                if (state.notes.isEmpty())
                    EmptyMessage()
                NoteList(navController = navController, notes = state.notes)
            }
        }

    }
}

@Composable
fun EmptyMessage() {
    Box(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Text(
            text = "Please add note below",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h2,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(16.dp)
        )
    }

}



@Composable
fun NoteList(navController: NavHostController, notes: List<Note>) {
    LazyColumn(state = rememberLazyListState()) {

        items(
            items = notes,
            key = { note ->
                note.id
            }
        ) { note ->
            NoteItem(note,  modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    navController.navigate(
                        Screen.AddNoteScreen.route +
                                "?noteId=${note.id}"
                    )
                })
            Spacer(modifier = Modifier.height(16.dp))
        }

    }
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

    AndroidnotesappTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                Toolbar()
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        //navController.navigate(Screen.AddEditNoteScreen.route)
                    },
                    backgroundColor = MaterialTheme.colors.primary
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add note")
                }
            }
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Note(
                1,
                "Liste des courses",
                "laudantium enim quasi est quidem magnam voluptate ipsam eos\\ntempora quo necessitatibus\\ndolor quam autem quasi\\nreiciendis et nam sapiente accusantium",
                System.currentTimeMillis(),
                Color(0xFF12355C).hashCode()
            ).let {
                NoteItem(it, modifier = Modifier
                    .padding(8.dp, 4.dp)
                    .fillMaxWidth()
                    .clickable {
                        /*navController.navigate(
                            Screen.AddEditNoteScreen.route +
                                    "?noteId=${note.id}&noteColor=${note.color}"
                        )*/
                    }
                    .height(140.dp)
                )
            }

        }


    }
}