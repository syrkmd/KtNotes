package com.yvl.notes.domain

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllNotesUseSase @Inject constructor(
    private val repository: NotesRepository
) {

    operator fun invoke(): Flow<List<Note>> {
        return repository.getAllNotes()
    }
}