package com.vhenri.mypy.di

import com.vhenri.mypy.network.ReplitApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create

@Module
object MainApiModule {
    @Provides
    fun providesReplitApi(retrofit: Retrofit): ReplitApi = retrofit.create()
}