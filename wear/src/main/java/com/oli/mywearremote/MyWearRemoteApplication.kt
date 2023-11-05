package com.oli.mywearremote

import android.app.Application
import androidx.annotation.RequiresApi
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyWearRemoteApplication: Application() {

    @RequiresApi(34)
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MyWearRemoteApplication)
            modules(appModule)
        }

    }

}