package com.yvl.notes.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.yvl.notes.presentation.screens.creation.CreateNoteScreen
import com.yvl.notes.presentation.screens.notes.NotesScreen
import com.yvl.notes.presentation.ui.theme.NotesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NotesTheme {
//                NotesScreen(
//                    onNoteClick = {
//                        Log.d("MainActivity", "onNoteClick $it")
//                    },
//                    onAddNoteClick = {
//                        Log.d("MainActivity", "onAddNoteClick")
//                    },
//                    onLongClick = {
//                        Log.d("MainActivity", "onLongClick")
//                    }
//                )
                CreateNoteScreen(
                    onFinished = {
                        Log.d("MainActivity", "onFinished")
                    }
                )
            }
        }
    }
}
