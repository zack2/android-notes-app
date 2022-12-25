package com.olivierloukombo.android_notes_app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.olivierloukombo.android_notes_app.ui.theme.*

@Entity
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val noteTitle: String,
    val noteDescription: String,
    val timestamp: Long,
    val color: Int,

    ) {

    companion object {
        val noteColors = listOf(purple, orange, cyan, MaastrichtBlue, Indigo)
    }
}
