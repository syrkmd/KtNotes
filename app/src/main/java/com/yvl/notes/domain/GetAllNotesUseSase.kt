package com.yvl.notes.domain

import kotlinx.coroutines.flow.Flow

class GetAllNotesUseSase(
    private val repository: NotesRepository
) {

    operator fun invoke(): Flow<List<Note>> {
        return repository.getAllNotes()
    }
}