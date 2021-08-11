package com.example.temper.network

import com.example.temper.models.ShiftModel
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface TemperApi {
    @GET("v3/shifts")
    fun getShiftsFromApi(@Query("filter[date]") date : String): Single<ShiftModel>
}