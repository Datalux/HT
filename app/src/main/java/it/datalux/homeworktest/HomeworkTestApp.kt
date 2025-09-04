package it.datalux.homeworktest

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HomeworkTestApp: Application() {
    override fun onCreate() {
        super.onCreate()

    }
}