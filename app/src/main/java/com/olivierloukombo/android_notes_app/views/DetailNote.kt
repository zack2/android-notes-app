package com.olivierloukombo.android_notes_app.views

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.olivierloukombo.android_notes_app.component.Toolbar
import com.olivierloukombo.android_notes_app.component.ToolbarDetail
import com.olivierloukombo.android_notes_app.component.TransparentHintTextField
import com.olivierloukombo.android_notes_app.ui.theme.AndroidnotesappTheme
import com.olivierloukombo.android_notes_app.vm.DetailNoteViewModel
import kotlinx.coroutines.flow.collectLatest

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailNote(navController: NavHostController, viewModel: DetailNoteViewModel = hiltViewModel()) {
    val titleState = viewModel.noteTitle.value
    val contentState = viewModel.noteDescription.value

    val scaffoldState = rememberScaffoldState()

/* val noteBackgroundAnimatable = remember {
    Anatable(
        Color(if (noteColor != -1) noteColor else viewModel.noteColor.value)
    )
}*/


    AndroidnotesappTheme {
        val ctx = LocalContext.current

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                ToolbarDetail(navController = navController)
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {

                        viewModel.saveNote()

                        Toast.makeText(ctx, "Note saved", Toast.LENGTH_SHORT).show()
                        navController.navigateUp()


                    },
                    backgroundColor = MaterialTheme.colors.primary
                ) {
                    Icon(imageVector = Icons.Default.Save, contentDescription = "Save note")
                }
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.background)
                    .padding(16.dp)
            ) {


                Spacer(modifier = Modifier.height(16.dp))
                TransparentHintTextField(
                    text = titleState.text,
                    hint = titleState.hint,
                    onValueChange = {
                        viewModel.onTitleChange(it)
                    },
                    onFocusChange = {
                        viewModel.onTitleChangeFocus(it)
                    },
                    isHintVisible = titleState.isHintVisible,
                    singleLine = true,
                    textStyle = MaterialTheme.typography.h5
                )
                Spacer(modifier = Modifier.height(16.dp))
                TransparentHintTextField(
                    text = contentState.text,
                    hint = contentState.hint,
                    onValueChange = {
                        viewModel.onValueChange(it)
                    },
                    onFocusChange = {
                        viewModel.contentWrite(it)
                    },
                    isHintVisible = contentState.isHintVisible,
                    textStyle = MaterialTheme.typography.body1,
                    modifier = Modifier.fillMaxHeight()
                )
            }


        }


    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun DetailPreview() {

    AndroidnotesappTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                Toolbar()
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                    },
                    backgroundColor = MaterialTheme.colors.primary
                ) {
                    Icon(imageVector = Icons.Default.Save, contentDescription = "Save note")
                }
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.background)
                    .padding(16.dp)
            ) {


                Spacer(modifier = Modifier.height(16.dp))
                TransparentHintTextField(
                    text = "titleState.text",
                    hint = "titleState.hint",
                    onValueChange = {
                    },
                    onFocusChange = {
                    },
                    isHintVisible = true,
                    singleLine = true,
                    textStyle = MaterialTheme.typography.h5
                )
                Spacer(modifier = Modifier.height(16.dp))
                TransparentHintTextField(
                    text = "contentState.text",
                    hint = "contentState.hint",
                    onValueChange = {
                        //  viewModel.onEvent(AddEditNoteEvent.EnteredContent(it))
                    },
                    onFocusChange = {
                        // viewModel.onEvent(AddEditNoteEvent.ChangeContentFocus(it))
                    },
                    isHintVisible = true,
                    textStyle = MaterialTheme.typography.body1,
                    modifier = Modifier.fillMaxHeight()
                )
            }


        }


    }
}