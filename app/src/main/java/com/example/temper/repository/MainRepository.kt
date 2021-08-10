package com.example.temper.repository

import com.example.temper.models.ShiftModel
import com.example.temper.network.TemperApi
import io.reactivex.Observable

class MainRepository constructor(private val api: TemperApi) {
    fun getShifts(date: String): Observable<ShiftModel> = api.getShiftsFromApi(date)
}