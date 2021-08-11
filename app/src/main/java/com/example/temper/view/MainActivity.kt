package com.example.temper.view

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.temper.R
import com.example.temper.adapters.ShiftAdapter
import com.example.temper.factory.MainViewModelFactory
import com.example.temper.helpers.*
import com.example.temper.models.Shift
import com.example.temper.models.ShiftModel
import com.example.temper.viewmodels.MainViewModel
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.component_action_buttons.*
import java.util.*

class MainActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    private lateinit var shiftsAdapter: ShiftAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this, MainViewModelFactory())[MainViewModel::class.java]

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
        viewModel.fetchShifts((Calendar.getInstance().time).converted())
    }

    // viewModel methods
    private fun initViewModel() {
        viewModel.shiftState().observe(this, {
            when (it.currentState) {
                0 -> loadingData()
                1 -> loadingDataSuccess(it.data as ShiftModel)
                -1 -> loadingDataError()
            }
        });
        viewModel.fetchShifts(txtDate.text.toString())
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
        fab.hide()
        shiftsAdapter.setUpdatedData(ArrayList<Shift>())
    }

    private fun loadingDataSuccess(data: ShiftModel) {
        fab.show()
        animWaiting.gone()
        swiperefresh.isRefreshing = false
        shiftsAdapter.setUpdatedData(data.data as ArrayList<Shift>)
    }

    private fun loadingDataError() {
        snackbarError("Something went wrong")
        animWaiting.gone()
        fab.show()
        swiperefresh.isRefreshing = false
        animNoData.visible()
    }

    override fun onDateSet(view: DatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        txtDate.text = "$year-${(monthOfYear + 1).withZero()}-${dayOfMonth.withZero()}"
        forceRefresh()
    }
}