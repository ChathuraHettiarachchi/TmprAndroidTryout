package com.example.temper.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.temper.data.ShiftModel
import com.example.temper.data.ShiftRepository
import com.example.temper.utils.Resource
import com.example.temper.utils.converted
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: ShiftRepository): ViewModel() {

    private var date: String = (Calendar.getInstance().time).converted()

    private val _shifts = MutableLiveData<Resource<ShiftModel>>()
    val shifts: LiveData<Resource<ShiftModel>> get() = _shifts

    fun requestShiftsFromTemper(dateToPass: String = date){
        viewModelScope.launch {
            _shifts.postValue(Resource.loading(null))
            try{
                repository.fetchShifts(dateToPass).let {
                    if(it.isSuccessful)
                        _shifts.postValue(Resource.success(it.body()))
                    else
                        _shifts.postValue(Resource.error(it.errorBody().toString(), null))
                }
            } catch(e: Exception){
                _shifts.postValue(Resource.error(e.message.toString(), null))
            }
        }
    }
}