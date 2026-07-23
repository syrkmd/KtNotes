package com.yvl.notes.domain

import kotlinx.coroutines.flow.Flow

class SearchNotesUseCase(
    private val repository: NotesRepository
) {

    suspend operator fun invoke(query: String): Flow<List<Note>> {
        return repository.searchNotes(query)
    }
}