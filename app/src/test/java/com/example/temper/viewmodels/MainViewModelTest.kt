package com.example.temper.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.temper.api.TemperApi
import com.example.temper.data.ShiftModel
import com.example.temper.data.ShiftRepository
import com.example.temper.ui.main.MainViewModel
import com.example.temper.util.TestCoroutineRule
import com.example.temper.utils.Resource
import com.example.temper.utils.converted
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response
import java.util.*

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {
    @get:Rule
    var testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var api: TemperApi

    @Mock
    private lateinit var repository: ShiftRepository

    @Mock
    private lateinit var apiShiftObserver: Observer<Resource<ShiftModel>>

    private lateinit var date: String

    @Before
    fun setup(){
        date = (Calendar.getInstance().time).converted()
    }

    @Test
    fun given_response200_when_fetch_should_return_success(){
        testCoroutineRule.runBlockingTest {
            `when`(api.fetchShifts(date)).thenReturn(Response.success(ShiftModel()))
            repository = ShiftRepository(api)

            val viewModel = MainViewModel(repository)
            viewModel.shifts.observeForever(apiShiftObserver)
            viewModel.requestShiftsFromTemper(date)

            verify(apiShiftObserver).onChanged(Resource.loading(null))
            verify(apiShiftObserver).onChanged(Resource.success(ShiftModel()))

            viewModel.shifts.removeObserver(apiShiftObserver)
        }
    }

    @Test
    fun given_serverError_when_fetch_should_return_error() {
        testCoroutineRule.runBlockingTest {

            val errorMessage = "Error on fetching data from Temper"

            `when`(api.fetchShifts(date)).thenThrow(RuntimeException(errorMessage))
            repository = ShiftRepository(api)

            val viewModel = MainViewModel(repository)
            viewModel.shifts.observeForever(apiShiftObserver)
            viewModel.requestShiftsFromTemper(date)

            verify(apiShiftObserver).onChanged(Resource.loading(null))
            verify(apiShiftObserver).onChanged(Resource.error(
                errorMessage,
                null
            ))

            viewModel.shifts.removeObserver(apiShiftObserver)
        }
    }

    @After
    fun tearDown() {

    }
}