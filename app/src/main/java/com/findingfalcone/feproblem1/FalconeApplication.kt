package com.findingfalcone.feproblem1


import android.app.Application
import android.content.Context
import com.findingfalcone.feproblem1.di.component.ApplicationComponent
import com.findingfalcone.feproblem1.di.component.DaggerApplicationComponent
import com.findingfalcone.feproblem1.di.module.ApplicationModule
import com.findingfalcone.feproblem1.utils.log.LogUtils
import io.paperdb.Paper


class FalconeApplication : Application() {

    var applicationComponent: ApplicationComponent? = null

    companion object {
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        injectDependencies()
        Paper.init(applicationContext)
        appContext = applicationContext
    }

    fun injectDependencies() {
        getApplicationComponent()
        applicationComponent?.let {
            it.inject(this)
        }
    }

    fun clearComponent() {
        applicationComponent = null
    }


    @JvmName("getApplicationComponent1")
    fun getApplicationComponent(): ApplicationComponent? {
        return if (applicationComponent == null) createDaggerComponent() else applicationComponent
    }

    fun createDaggerComponent(): ApplicationComponent? {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        return applicationComponent
    }

}