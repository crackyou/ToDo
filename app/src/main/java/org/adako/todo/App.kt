package org.adako.todo

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject


@HiltAndroidApp
class App: Application() {

    @Inject
    lateinit var timberTree: Timber.Tree

    override fun onCreate() {
        super.onCreate()
        instance = this

        Timber.plant(timberTree)

    }

    companion object{
        lateinit var instance: App
            private set
    }


}