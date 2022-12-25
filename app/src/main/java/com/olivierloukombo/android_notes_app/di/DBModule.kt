package com.olivierloukombo.android_notes_app.di

import android.app.Application
import androidx.room.Room
import com.olivierloukombo.android_notes_app.data.NoteDatabase
import com.olivierloukombo.android_notes_app.data.repository.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DBModule {
    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase {
        return NoteDatabase.getInstance(app)
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDatabase): NoteRepository {
        return NoteRepository(db.noteDao())
    }

}