package com.findingfalcone.feproblem1.ui.module.FindQueenScreen

import androidx.lifecycle.MutableLiveData
import com.findingfalcone.feproblem1.data.remote.Networking
import com.findingfalcone.feproblem1.data.remote.response.FindFalconeSolutionResponse
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
    var FalconeFind: MutableLiveData<FindFalconeSolutionResponse>? = MutableLiveData<FindFalconeSolutionResponse>()

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
    fun onVehiclesList(value: ArrayList<VehicleApiResponse.VehicleResponseItem>) = vehiclesList.postValue(value)

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
                            FalconeFind?.postValue(it)
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

    /*fun updatePlanetDetail(vehicleIndex: Int) {
        val selectedPlanet = selectedPlanet!!.value
        val selectedPlanetPosition = selectedPlanetPosition!!.value
        selectedPlanet!!.vehicle = mVehicleList.value!![vehicleIndex]
        mPlanetList!!.value!!.set(selectedPlanetPosition!!, selectedPlanet)
        var timeTake: Int = 0
        var selectedPlanetCount = 0
        for (item in mPlanetList!!.value!!) {
            if (item!!.vehicle != null) {
                try {
                    val time: Int
                    val distance = item.distance!!
                    val speed = item.vehicle!!.speed!!
                    time = distance / speed
                    timeTake += timeTake + time
                } catch (e: NullPointerException) {
                } catch (e: Exception) {

                }
                selectedPlanetCount++
            }
        }
        if (selectedPlanetCount == 4) isFindFalconeEnable!!.postValue(true)
        timeTaken?.postValue(timeTake)
    }*/


}

