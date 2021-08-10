package com.example.temper.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.temper.helpers.converted
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import java.util.*


class MainViewModelTest{

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val rxSchedulerRule = RxSchedulerRule()

    @InjectMocks
    private lateinit var classUnderTest: MainViewModel

    @Before
    fun init(){
        //classUnderTest = MainViewModel(MainRepository(TemperApi::class.java))
    }

    @Test
    fun init_shift_model_is_not_empty() {
        val shiftData = classUnderTest.shiftListLiveData.testObserver()
        assertThat(shiftData.observedValues).isNotNull()
    }

    @Test
    fun onShiftRequest_set_correct_loading_states() {
        val status = classUnderTest.loadingStatus.testObserver()
        classUnderTest.fetchShifts((Calendar.getInstance().time).converted());

        assertThat(status.observedValues)
            .isEqualTo(listOf(MainViewModel.LoadingStatus.LOADING,
                MainViewModel.LoadingStatus.NOT_LOADING
            ))
    }
}