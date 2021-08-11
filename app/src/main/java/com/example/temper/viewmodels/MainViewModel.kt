package com.example.temper.viewmodels

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.temper.helpers.RxSingleSchedulers
import com.example.temper.helpers.converted
import com.example.temper.models.ShiftModel
import com.example.temper.network.TemperApi
import com.example.temper.network.TemperInstance
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Retrofit
import java.util.*

class MainViewModel: ViewModel() {

    private var disposable: CompositeDisposable? = null
    private var rxSingleSchedulers: RxSingleSchedulers? = null
    private var apiClient: Retrofit? = null

    private val _loadingStatus = MutableLiveData<ShiftViewState>()

    init {
        this.disposable = CompositeDisposable()
        this.rxSingleSchedulers = RxSingleSchedulers.DEFAULT
        this.apiClient = TemperInstance.getTemperInstance()
    }

    fun shiftState(): MutableLiveData<ShiftViewState> = _loadingStatus

    @SuppressLint("CheckResult")
    fun fetchShifts(date: String?){
        val dateSelected = if (date.isNullOrEmpty()) (Calendar.getInstance().time).converted() else date

        disposable!!.add(
            apiClient!!.create(TemperApi::class.java).getShiftsFromApi(dateSelected)
                .doOnEvent { (_), _ -> onLoading() }
                .compose(rxSingleSchedulers!!.applySchedulers())
                .subscribe(
                    { shiftModel: ShiftModel? -> this.onSuccess(shiftModel!!) },
                    { error: Throwable? -> onError(error!!) })
        );
    }

    private fun onSuccess(shiftModel: ShiftModel) {
        ShiftViewState.SUCCESS_STATE.data = shiftModel
        _loadingStatus.postValue(ShiftViewState.SUCCESS_STATE)
    }

    private fun onError(error: Throwable) {
        ShiftViewState.ERROR_STATE.error = error
        _loadingStatus.postValue(ShiftViewState.ERROR_STATE)
    }

    private fun onLoading() {
        _loadingStatus.postValue(ShiftViewState.LOADING_STATE)
    }

    override fun onCleared() {
        super.onCleared()
        if (disposable != null) {
            disposable!!.clear()
            disposable = null
        }
    }
}