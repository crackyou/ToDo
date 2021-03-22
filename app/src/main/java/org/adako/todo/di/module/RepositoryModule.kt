package org.adako.todo.di.module

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.adako.todo.data.ToDoDatabase
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun provideToDoDatabase(
        @ApplicationContext appContext: Context
    ): ToDoDatabase {
        return Room.databaseBuilder(
            appContext,
            ToDoDatabase::class.java,
            "db.db"
        )
            .build()
    }

    @Provides
    @Singleton
    fun provideToDoDao(appDatabase: ToDoDatabase) = appDatabase.getToDoDao()

}