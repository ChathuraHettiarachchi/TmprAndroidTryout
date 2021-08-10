package com.example.temper.viewmodels

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.temper.models.ShiftModel
import com.example.temper.repository.MainRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.*

class MainViewModel(private val repository: MainRepository): ViewModel() {
    enum class LoadingStatus { LOADING, NOT_LOADING }

    private val _shiftListLiveData: MutableLiveData<ShiftModel> = MutableLiveData()
    val shiftListLiveData : LiveData<ShiftModel> = _shiftListLiveData

    private val _loadingStatus = MutableLiveData<LoadingStatus>()
    val loadingStatus: LiveData<LoadingStatus> = _loadingStatus

    // fetch shifts using coroutines
    @SuppressLint("CheckResult")
    fun fetchShifts(date: String?){
        val dateSelected = if (date.isNullOrEmpty()) Calendar.getInstance().time else date

        repository.getShifts(dateSelected.toString())
            .subscribeOn(io.reactivex.schedulers.Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                _loadingStatus.postValue(LoadingStatus.LOADING)
            }
            .doFinally { _loadingStatus.postValue(LoadingStatus.NOT_LOADING) }
            .subscribe({
                if(it !=null)
                    _shiftListLiveData.postValue(it)
            },{})
    }
}