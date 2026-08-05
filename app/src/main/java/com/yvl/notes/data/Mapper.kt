package com.yvl.notes.data

import com.yvl.notes.domain.ContentItem
import com.yvl.notes.domain.Note
import kotlinx.serialization.json.Json

fun Note.toDbModel(): NoteDbModel {
    val contentAsString = Json.encodeToString(content.toContentItemToDbModels())
    return NoteDbModel(id, title, contentAsString, updatedAt, isPinned)
}

fun List<ContentItem>.toContentItemToDbModels(): List<ContentItemDbModel> {
    return map { contentItem ->
        when (contentItem) {
            is ContentItem.Image -> {
                ContentItemDbModel.Image(contentItem.url)
            }

            is ContentItem.Text -> {
                ContentItemDbModel.Text(contentItem.content)
            }
        }
    }
}

fun List<ContentItemDbModel>.toContentItems(): List<ContentItem> {
    return map { contentItemDbModel ->
        when (contentItemDbModel) {
            is ContentItemDbModel.Image -> {
                ContentItem.Image(contentItemDbModel.url)
            }

            is ContentItemDbModel.Text -> {
                ContentItem.Text(contentItemDbModel.content)
            }
        }
    }
}

fun NoteDbModel.toEntity(): Note {
    val contentItemDbModels = Json.decodeFromString<List<ContentItemDbModel>>(content)
    return Note(id, title, contentItemDbModels.toContentItems(), updatedAt, isPinned)
}

fun List<NoteDbModel>.toEntities(): List<Note> {
    return map {
        it.toEntity()
    }
}