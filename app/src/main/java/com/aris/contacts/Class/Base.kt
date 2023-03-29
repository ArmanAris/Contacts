package com.aris.contacts.Class

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import androidx.fragment.app.FragmentManager

@SuppressLint("StaticFieldLeak")
object Base : Application() {

    var fragmentManager: FragmentManager? = null
    var activity: Activity? = null

    override fun onCreate() {
        super.onCreate()

    }
}