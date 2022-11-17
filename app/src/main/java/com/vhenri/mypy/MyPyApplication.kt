package com.vhenri.mypy

import android.app.Application
import com.vhenri.mypy.di.AppComponent
import com.vhenri.mypy.di.DaggerAppComponent

open class MyPyApplication : Application() {
    // Instance of the AppComponent that will be used by all the Activities in the project
    val appComponent : AppComponent by lazy {
        initComponent()
    }

    open fun initComponent(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }
}