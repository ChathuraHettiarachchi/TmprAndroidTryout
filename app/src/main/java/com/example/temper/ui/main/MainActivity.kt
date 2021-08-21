package com.example.temper.ui.main

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.temper.R
import com.example.temper.data.Shift
import com.example.temper.data.ShiftModel
import com.example.temper.ui.login.LoginActivity
import com.example.temper.ui.subscribe.SubscribeActivity
import com.example.temper.utils.*
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.component_action_buttons.*
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    private lateinit var shiftsAdapter: ShiftAdapter
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        initDatePicker()
        initViewModel()
        addActionListeners()
    }

    // init main views
    private fun initViews() {
        fab.hide()
        swiperefresh.isRefreshing = true
        swiperefresh.setOnRefreshListener {
            forceRefresh()
        }

        animWaiting.visible()
        animNoData.gone()

        if (recyclerViewShift != null) {
            recyclerViewShift.layoutManager = LinearLayoutManager(this)
            shiftsAdapter = ShiftAdapter()
            recyclerViewShift.adapter = shiftsAdapter
            recyclerViewShift.attachFab(fab)
        }
    }

    // force refresh method
    private fun forceRefresh() {
        viewModel.requestShiftsFromTemper((Calendar.getInstance().time).converted())
    }

    // viewModel methods
    private fun initViewModel() {
        viewModel.shifts.observe(this, {
            when (it.status) {
                Status.LOADING -> loadingData()
                Status.SUCCESS -> loadingDataSuccess(it.data as ShiftModel)
                Status.ERROR -> loadingDataError()
                Status.NO_NETWORK -> loadingDataNoNetwork()
            }
        });
        forceRefresh()
    }

    // datePicker methods
    private fun initDatePicker() {
        val now = Calendar.getInstance()
        txtDate.text = (now.time).converted()

        txtDate.setOnClickListener {
            val datePicker = DatePickerDialog.newInstance(
                this,
                now[Calendar.YEAR],
                now[Calendar.MONTH],
                now[Calendar.DAY_OF_MONTH]
            )
            datePicker.accentColor = Color.parseColor("#2DC53C")
            datePicker.show(supportFragmentManager, "Datepickerdialog");
        }
    }

    // change activity
    private fun addActionListeners() {
        btnLogin.setOnClickListener { startActivity(Intent(this, LoginActivity::class.java)) }
        btnSubscribe.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    SubscribeActivity::class.java
                )
            )
        }
        fab.setOnClickListener {
            snackbarWarning("Oopz, filter feature is not implemented yet")
        }
    }

    private fun loadingData() {
        swiperefresh.isRefreshing = true
        animWaiting.visible()
        animNoNetwork.gone()
        animNoData.gone()
        fab.hide()
        shiftsAdapter.setUpdatedData(ArrayList<Shift>())
    }

    private fun loadingDataSuccess(data: ShiftModel) {
        fab.show()
        animWaiting.gone()
        animNoNetwork.gone()
        swiperefresh.isRefreshing = false
        shiftsAdapter.setUpdatedData(data.data as ArrayList<Shift>)
    }

    private fun loadingDataError() {
        snackbarError("Something went wrong")
        animWaiting.gone()
        animNoNetwork.gone()
        fab.show()
        swiperefresh.isRefreshing = false
        animNoData.visible()
    }

    private fun loadingDataNoNetwork() {
        snackbarError("No network found")
        animWaiting.gone()
        fab.show()
        swiperefresh.isRefreshing = false
        animNoData.gone()
        animNoNetwork.visible()
    }

    @SuppressLint("SetTextI18n")
    override fun onDateSet(view: DatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        txtDate.text = "$year-${(monthOfYear + 1).withZero()}-${dayOfMonth.withZero()}"
        forceRefresh()
    }
}