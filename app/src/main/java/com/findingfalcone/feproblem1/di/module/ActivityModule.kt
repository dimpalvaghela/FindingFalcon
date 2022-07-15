package com.findingfalcone.feproblem1.di.module

import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.findingfalcone.feproblem1.data.repository.FalconeSolutionRepository
import com.findingfalcone.feproblem1.data.repository.FindingFalconeRepository
import com.findingfalcone.feproblem1.ui.base.BaseActivity
import com.findingfalcone.feproblem1.ui.module.FindQueenScreen.FindViewModel
import com.findingfalcone.feproblem1.ui.module.OutPutScreen.FindFalconeSolutionModel
import com.findingfalcone.feproblem1.utils.ViewModelProviderFactory
import com.findingfalcone.feproblem1.utils.network.NetworkHelper
import com.findingfalcone.feproblem1.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

/**
 * Kotlin Generics Reference: https://kotlinlang.org/docs/reference/generics.html
 * Basically it means that we can pass any class that extends BaseActivity which take
 * BaseViewModel subclass as parameter
 */
@Module
class ActivityModule(private val activity: BaseActivity<*>) {

    @Provides
    fun provideLinearLayoutManager(): LinearLayoutManager = LinearLayoutManager(activity)


     @Provides
     fun provideTokenViewModel(
         schedulerProvider: SchedulerProvider,
         compositeDisposable: CompositeDisposable,
         networkHelper: NetworkHelper,
         findingFalconeRepository: FindingFalconeRepository
     ): FindViewModel = ViewModelProviders.of(
         activity, ViewModelProviderFactory(FindViewModel::class) {
             FindViewModel(schedulerProvider, compositeDisposable, networkHelper, findingFalconeRepository)
         }).get(FindViewModel::class.java)



    @Provides
    fun FalconeSolutionRepository(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        falconeSolutionRepository: FalconeSolutionRepository
    ): FindFalconeSolutionModel = ViewModelProviders.of(
        activity, ViewModelProviderFactory(FindFalconeSolutionModel::class) {
            FindFalconeSolutionModel(schedulerProvider, compositeDisposable, networkHelper, falconeSolutionRepository)
        }).get(FindFalconeSolutionModel::class.java)

}