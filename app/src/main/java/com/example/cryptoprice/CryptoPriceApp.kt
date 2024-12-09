package com.example.cryptoprice

import android.app.Application
import com.example.cryptoprice.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CryptoPriceApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CryptoPriceApp)
            androidLogger()

            modules(appModule)
        }
    }
}