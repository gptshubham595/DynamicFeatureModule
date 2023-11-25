package com.example.dynamicfeaturemodule

import android.app.Application
import android.content.Context
import com.google.android.play.core.splitcompat.SplitCompat

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        SplitCompat.install(this)
    }
}