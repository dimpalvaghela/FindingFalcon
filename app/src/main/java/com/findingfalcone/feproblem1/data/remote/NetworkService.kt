package com.findingfalcone.feproblem1.data.remote

import com.findingfalcone.feproblem1.data.remote.response.*
import io.reactivex.Single
import retrofit2.http.*
import javax.inject.Singleton

@Singleton
interface NetworkService {

    @Headers("Accept: application/json")
    @POST(Endpoints.TOKEN)
    fun getToken(): Single<TokenApiResponse>

    @GET(Endpoints.PLANETS)
    fun getPlanetsList(): Single<List<PlanetsApiResponse.PlanetResponseItem>>

    @GET(Endpoints.VEHICLES)
    fun getVehiclesList(): Single<List<VehicleApiResponse.VehicleResponseItem>>

    @Headers("Accept: application/json", "Content-Type: application/json")
    @POST(Endpoints.FIND)
    fun findPrinces(@Body body: FindApiRequest): Single<FindApiRequest>





}