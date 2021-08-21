package com.example.temper.api

import com.example.temper.data.ShiftModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TemperApi {

    @GET("v3/shifts")
    suspend fun fetchShifts(@Query("filter[date]") date : String): Response<ShiftModel>
}