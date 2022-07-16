package com.findingfalcone.feproblem1.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.findingfalcone.feproblem1.BuildConfig
import com.findingfalcone.feproblem1.FalconeApplication
import com.findingfalcone.feproblem1.data.remote.NetworkService
import com.findingfalcone.feproblem1.data.remote.Networking
import com.findingfalcone.feproblem1.di.ApplicationContext
import com.findingfalcone.feproblem1.di.ResourcesProvider
import com.findingfalcone.feproblem1.utils.common.Constants
import com.findingfalcone.feproblem1.utils.network.NetworkHelper
import com.findingfalcone.feproblem1.utils.rx.RxSchedulerProvider
import com.findingfalcone.feproblem1.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: FalconeApplication) {

    @Provides
    @Singleton
    fun provideApplication(): Application = application

    @Provides
    @Singleton
    @ApplicationContext
    fun provideContext(): Context = application

    /**
     * Since this function do not have @Singleton then each time CompositeDisposable is injected
     * then a new instance of CompositeDisposable will be provided
     */
    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    fun provideSchedulerProvider(): SchedulerProvider = RxSchedulerProvider()

    @Provides
    @Singleton
    fun provideSharedPreferences(): SharedPreferences =
        application.getSharedPreferences(Constants.PREFS_NAME, Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideNetworkService(): NetworkService =
        Networking.create(
            BuildConfig.BASE_URL,
            application.cacheDir,
            10 * 1024 * 1024 // 10MB
        )

    @Singleton
    @Provides
    fun provideNetworkHelper(): NetworkHelper = NetworkHelper(application)

    @Singleton
    @Provides
    fun provideResourceProvider(): ResourcesProvider = ResourcesProvider(application)
}