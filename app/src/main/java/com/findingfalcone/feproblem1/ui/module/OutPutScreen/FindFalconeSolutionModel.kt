package com.findingfalcone.feproblem1.ui.module.OutPutScreen

import androidx.lifecycle.MutableLiveData
import com.findingfalcone.feproblem1.data.remote.Networking
import com.findingfalcone.feproblem1.data.remote.response.PlanetsApiResponse
import com.findingfalcone.feproblem1.data.remote.response.VehicleApiResponse
import com.findingfalcone.feproblem1.data.repository.FalconeSolutionRepository
import com.findingfalcone.feproblem1.data.repository.FindingFalconeRepository
import com.findingfalcone.feproblem1.ui.base.BaseViewModel
import com.findingfalcone.feproblem1.utils.log.Logger
import com.findingfalcone.feproblem1.utils.network.NetworkHelper
import com.findingfalcone.feproblem1.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable


class FindFalconeSolutionModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    falconeSolutionRepository: FalconeSolutionRepository
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {
    val isDisplayProgressDialog: MutableLiveData<Boolean> = MutableLiveData()
    var planetsList: MutableLiveData<ArrayList<PlanetsApiResponse.PlanetResponseItem>> = MutableLiveData(ArrayList())
    var vehiclesList: MutableLiveData<ArrayList<VehicleApiResponse.VehicleResponseItem>> = MutableLiveData(ArrayList())

    override fun onCreate() {
    }


   /* fun getPlanetsList() {
        Logger.e("Networking.API_KEY",  Networking.HEADER_AUTH_TOKEN)
        if (checkInternetConnectionWithMessage()) {
            isDisplayProgressDialog.postValue(true)
            compositeDisposable.addAll(
                findingFalconeRepository.getPlanets()
                    .subscribeOn(schedulerProvider.io())
                    .subscribe(
                        {
                            isDisplayProgressDialog.postValue(false)
                            onPlanetsList(ArrayList(it))
                        },
                        {
                            isDisplayProgressDialog.postValue(false)
                            handleNetworkError(it)
                        }
                    )
            )
        } else {
        }

    }
    fun onPlanetsList(value: ArrayList<PlanetsApiResponse.PlanetResponseItem>) = planetsList.postValue(value)
*/



}

