package com.example.temper.data

import com.example.temper.api.TemperApi
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShiftRepository @Inject constructor(private val temperApi: TemperApi){
    suspend fun fetchShifts(date: String): Response<ShiftModel> = temperApi.fetchShifts(date)
}