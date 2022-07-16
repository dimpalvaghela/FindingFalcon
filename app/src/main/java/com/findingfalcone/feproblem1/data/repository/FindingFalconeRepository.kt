package com.findingfalcone.feproblem1.data.repository

import com.findingfalcone.feproblem1.data.local.prefs.ConstantsPref
import com.findingfalcone.feproblem1.data.local.prefs.PaperDbHelper
import com.findingfalcone.feproblem1.data.remote.NetworkService
import com.findingfalcone.feproblem1.data.remote.response.FindApiRequest
import com.findingfalcone.feproblem1.data.remote.response.FindFalconeSolutionResponse
import com.findingfalcone.feproblem1.data.remote.response.PlanetsApiResponse
import com.findingfalcone.feproblem1.data.remote.response.VehicleApiResponse
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FindingFalconeRepository @Inject constructor(
    private val networkService: NetworkService,
) {

    fun getToken(): Completable = networkService.getToken()
        .map {
            PaperDbHelper.write(ConstantsPref.API_TOKEN, it.token)
        }
        .ignoreElement()
        .onErrorResumeNext { Completable.error(it) }
        .subscribeOn(Schedulers.io())


    fun getPlanets(): Single<List<PlanetsApiResponse.PlanetResponseItem>> =
        networkService.getPlanetsList()
            .map {
                it
            }

    fun getVehicles(): Single<List<VehicleApiResponse.VehicleResponseItem>> =
        networkService.getVehiclesList()
            .map {
                it
            }

    var fToken = PaperDbHelper.read(ConstantsPref.API_TOKEN,"")

    fun findPrinces(planets:  List<String>, vehicles: List<String>): Single<FindFalconeSolutionResponse> =
        networkService.findPrinces(FindApiRequest(fToken,planets,vehicles))
            .map {
                it
            }
}