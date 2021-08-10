package com.example.temper.factory

import com.example.temper.network.TemperApi
import com.example.temper.network.TemperInstance
import com.example.temper.repository.MainRepository

object RepositoryFactory {
    fun createMainRepository() : MainRepository {
        val api = TemperInstance.getTemperInstance().create(TemperApi::class.java)
        return MainRepository(api)
    }
}