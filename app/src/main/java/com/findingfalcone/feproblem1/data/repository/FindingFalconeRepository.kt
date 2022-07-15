package com.findingfalcone.feproblem1.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.findingfalcone.domain.model.FindResponse
import com.example.findingfalcone.domain.model.Planet
import com.example.findingfalcone.domain.model.Vehicle
import com.findingfalcone.feproblem1.data.local.db.DatabaseService
import com.findingfalcone.feproblem1.data.local.prefs.ConstantsPref
import com.findingfalcone.feproblem1.data.local.prefs.PaperDbHelper
import com.findingfalcone.feproblem1.data.remote.NetworkService
import com.findingfalcone.feproblem1.data.remote.response.FindApiRequest
import com.findingfalcone.feproblem1.data.remote.response.PlanetsApiResponse
import com.findingfalcone.feproblem1.data.remote.response.VehicleApiResponse
import com.findingfalcone.feproblem1.utils.log.Logger
import com.google.gson.GsonBuilder
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.http.Body
import javax.inject.Inject

class FindingFalconeRepository @Inject constructor(
    private val networkService: NetworkService,
    private val databaseService: DatabaseService,

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

    fun findPrinces(planets:  List<String>, vehicles: List<String>): Single<FindApiRequest> =
        networkService.findPrinces(FindApiRequest(fToken,planets,vehicles))
            .map {
                it
            }
}