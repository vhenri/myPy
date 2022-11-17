package com.vhenri.mypy.di

import android.content.Context
import com.vhenri.mypy.ui.main.ExecFragment
import com.vhenri.mypy.ui.main.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, MainApiModule::class])
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(activity: MainActivity)
    fun inject(fragment: ExecFragment)
}