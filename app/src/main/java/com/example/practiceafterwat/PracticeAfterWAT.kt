package com.example.practiceafterwat

import android.app.Application
import com.example.practiceafterwat.di.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class PracticeAfterWAT : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@PracticeAfterWAT)
            modules(
                listOf(
                    dispatcherModule,
                    localModule,
                    remoteModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}