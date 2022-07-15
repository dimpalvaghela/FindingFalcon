package com.findingfalcone.feproblem1.ui.module.FindQueenScreen

import androidx.lifecycle.MutableLiveData
import com.findingfalcone.feproblem1.data.remote.Networking
import com.findingfalcone.feproblem1.data.remote.response.PlanetsApiResponse
import com.findingfalcone.feproblem1.data.remote.response.VehicleApiResponse
import com.findingfalcone.feproblem1.data.repository.FindingFalconeRepository
import com.findingfalcone.feproblem1.ui.base.BaseViewModel
import com.findingfalcone.feproblem1.utils.log.Logger
import com.findingfalcone.feproblem1.utils.network.NetworkHelper
import com.findingfalcone.feproblem1.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable


class FindViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val findingFalconeRepository: FindingFalconeRepository
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {
    val isDisplayProgressDialog: MutableLiveData<Boolean> = MutableLiveData()
    var planetsList: MutableLiveData<ArrayList<PlanetsApiResponse.PlanetResponseItem>> = MutableLiveData(ArrayList())
    var vehiclesList: MutableLiveData<ArrayList<VehicleApiResponse.VehicleResponseItem>> = MutableLiveData(ArrayList())

    override fun onCreate() {
        setToken()
    }


    private fun setToken() {
        findingFalconeRepository.getToken()
            .doOnError { handleNetworkError(it) }
            .subscribe()
    }

    fun getPlanetsList() {
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

    fun getVehicleList() {
        Logger.e("Networking.API_KEY",  Networking.HEADER_AUTH_TOKEN)
        if (checkInternetConnectionWithMessage()) {
            isDisplayProgressDialog.postValue(true)
            compositeDisposable.addAll(
                findingFalconeRepository.getVehicles()
                    .subscribeOn(schedulerProvider.io())
                    .subscribe(
                        {
                            isDisplayProgressDialog.postValue(false)
                            onVehiclesList(ArrayList(it))
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

    fun getQueen(planets:  List<String>, vehicles: List<String>) {
        Logger.e("Networking.API_KEY",  Networking.HEADER_AUTH_TOKEN)
        if (checkInternetConnectionWithMessage()) {
            isDisplayProgressDialog.postValue(true)
            compositeDisposable.addAll(
                findingFalconeRepository.findPrinces(planets,vehicles)
                    .subscribeOn(schedulerProvider.io())
                    .subscribe(
                        {
                            isDisplayProgressDialog.postValue(false)
                            it
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
    fun onVehiclesList(value: ArrayList<VehicleApiResponse.VehicleResponseItem>) = vehiclesList.postValue(value)



}

