package com.borja.android.borchxat

import android.app.Application
import com.google.firebase.FirebaseApp
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BorchXatApp:Application() {

    override fun onCreate() {
        super.onCreate()
            FirebaseApp.initializeApp(this)
    }
}