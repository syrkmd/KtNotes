package com.yvl.notes.data

import com.yvl.notes.domain.ContentItem
import com.yvl.notes.domain.Note
import kotlinx.serialization.json.Json

fun Note.toDbModel(): NoteDbModel {
    return NoteDbModel(id, title, updatedAt, isPinned)
}

fun List<ContentItem>.toContentItemToDbModels(noteId: Int): List<ContentItemDbModel> {
    return mapIndexed { index, contentItem ->
       when (contentItem) {
           is ContentItem.Image -> {
               ContentItemDbModel(
                   noteId = noteId,
                   contentType = ContentType.IMAGE,
                   content = contentItem.url,
                   order = index
               )
           }
           is ContentItem.Text -> {
               ContentItemDbModel(
                   noteId = noteId,
                   contentType = ContentType.TEXT,
                   content = contentItem.content,
                   order = index
               )
           }
       }
    }
}

fun List<ContentItemDbModel>.toContentItems(): List<ContentItem> {
    return map { contentItemDbModel ->
        when(contentItemDbModel.contentType) {
            ContentType.TEXT -> {
                ContentItem.Text(
                    content = contentItemDbModel.content
                )
            }
            ContentType.IMAGE -> {
                ContentItem.Image(
                    url = contentItemDbModel.content
                )
            }
        }
    }
}

fun NoteWithContentDbModel.toEntity(): Note {
    return Note(
        id = noteDbModel.id,
        title = noteDbModel.title,
        content = content.toContentItems(),
        updatedAt = noteDbModel.updatedAt,
        isPinned = noteDbModel.isPinned
    )
}

fun List<NoteWithContentDbModel>.toEntities(): List<Note> {
    return map {
        it.toEntity()
    }
}