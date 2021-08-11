package com.example.temper.viewmodels

import com.example.temper.base.BaseViewState
import com.example.temper.models.ShiftModel

class ShiftViewState private constructor(data: ShiftModel?, currentState: Int, error: Throwable?) :
    BaseViewState<ShiftModel>() {

    init {
        this.data = data
        this.currentState = currentState
        this.error = error
    }

    companion object {
        var ERROR_STATE: ShiftViewState =
            ShiftViewState(null, State.FAILED.value, Throwable())
        var LOADING_STATE: ShiftViewState = ShiftViewState(null, State.LOADING.value, null)
        var SUCCESS_STATE: ShiftViewState =
            ShiftViewState(ShiftModel(), State.SUCCESS.value, null)
    }
}