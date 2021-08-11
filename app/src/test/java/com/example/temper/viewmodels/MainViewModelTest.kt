package com.example.temper.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer
import com.example.temper.helpers.converted
import com.example.temper.models.ShiftModel
import com.example.temper.network.TemperApi
import com.example.temper.network.TemperInstance
import io.reactivex.Single
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.*
import org.mockito.Mockito.mock

import retrofit2.Retrofit
import java.util.*

@RunWith(JUnit4::class)
class MainViewModelTest {
    @get:Rule
    var instantExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    private var viewModel: MainViewModel? = null
    private lateinit var date: String

    @Mock
    lateinit var observer: Observer<ShiftViewState>

    @Mock
    lateinit var apiClient: TemperInstance

    @Mock
    lateinit var lifecycleOwner: LifecycleOwner
    lateinit var lifecycle: Lifecycle

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        lifecycle = LifecycleRegistry(lifecycleOwner)
        viewModel = MainViewModel()
        viewModel!!.shiftState().observeForever(observer)
        apiClient = TemperInstance()
        date = (Calendar.getInstance().time).converted()
    }

    @Test
    fun testApiFetchToNull() {
        Mockito.`when`(apiClient.fetchShifts(date))
            .thenReturn(null)
        Assert.assertNotNull(viewModel!!.shiftState())
        Assert.assertTrue(viewModel!!.shiftState().hasObservers())
    }

    @Test
    fun testApiFetchShiftToSuccess() {
        Mockito.`when`(apiClient.fetchShifts(date)).thenReturn(Single.just(
            ShiftModel()
        ))
        viewModel!!.fetchShifts(date)
        Mockito.verify(observer).onChanged(ShiftViewState.LOADING_STATE)
        Mockito.verify(observer).onChanged(ShiftViewState.SUCCESS_STATE)
    }

    @Test
    fun testApiFetchShiftToError() {
        Mockito.`when`(apiClient.fetchShifts(date)).thenReturn(Single.error(Throwable("Api error")))
        viewModel!!.fetchShifts(date)
        Mockito.verify(observer).onChanged(ShiftViewState.LOADING_STATE)
        Mockito.verify(observer).onChanged(ShiftViewState.ERROR_STATE)
    }

    @After
    @Throws(java.lang.Exception::class)
    fun tearDown() {
        viewModel = null
    }
}