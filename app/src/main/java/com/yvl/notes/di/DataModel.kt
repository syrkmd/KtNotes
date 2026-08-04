package com.yvl.notes.di

import android.content.Context
import com.yvl.notes.data.NoteDataBase
import com.yvl.notes.data.NotesDao
import com.yvl.notes.data.NotesRepositoryImpl
import com.yvl.notes.domain.NotesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModel {

    @Singleton
    @Binds
    fun bindNotesRepository(
        impl: NotesRepositoryImpl
    ): NotesRepository

    companion object {

        @Singleton
        @Provides
        fun provideDataBase(
            @ApplicationContext context: Context
        ): NoteDataBase {
            return NoteDataBase.getInstance(context)
        }

        @Singleton
        @Provides
        fun provideNotesDao(
            dataBase: NoteDataBase
        ): NotesDao {
            return dataBase.notesDao()
        }
    }
}