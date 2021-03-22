package org.adako.todo.di.module

import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.adako.todo.BuildConfig
import timber.log.Timber
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object TimberModule {

    @Provides
    @Singleton
    fun provideTimberTree(): Timber.Tree =
        object : Timber.DebugTree() {
            override fun isLoggable(tag: String?, priority: Int) =
                BuildConfig.DEBUG || priority >= Log.INFO
        }

}