package com.findingfalcone.feproblem1.data.repository

import com.findingfalcone.feproblem1.data.local.db.DatabaseService
import com.findingfalcone.feproblem1.data.local.prefs.ConstantsPref
import com.findingfalcone.feproblem1.data.local.prefs.PaperDbHelper
import com.findingfalcone.feproblem1.data.remote.NetworkService
import com.findingfalcone.feproblem1.data.remote.response.FindApiRequest
import com.findingfalcone.feproblem1.data.remote.response.PlanetsApiResponse
import com.findingfalcone.feproblem1.data.remote.response.VehicleApiResponse
import com.google.gson.GsonBuilder
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FalconeSolutionRepository @Inject constructor(
    private val networkService: NetworkService,
    private val databaseService: DatabaseService,

) {




}