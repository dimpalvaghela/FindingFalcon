package com.findingfalcone.feproblem1.di.component

import com.findingfalcone.feproblem1.di.ActivityScope
import com.findingfalcone.feproblem1.di.module.ActivityModule
import com.findingfalcone.feproblem1.ui.module.FindQueenScreen.FindActivity
import dagger.Component

@ActivityScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ActivityModule::class]
)
interface ActivityComponent {

    fun inject(activity: FindActivity)


}