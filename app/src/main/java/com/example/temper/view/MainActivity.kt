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
import com.example.temper.factory.RepositoryFactory
import com.example.temper.helpers.*
import com.example.temper.models.Shift
import com.example.temper.viewmodels.MainViewModel
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.component_action_buttons.*
import java.util.*

class MainActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener{

    private lateinit var shiftsAdapter: ShiftAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this, MainViewModelFactory(RepositoryFactory.createMainRepository())).get(MainViewModel::class.java)

        initViews()
        initDatePicker()
        initViewModel()
        addActionListeners()
    }

    // init main views
    private fun initViews(){
        fab.hide()
        swiperefresh.isRefreshing = true
        swiperefresh.setOnRefreshListener {
            forceRefresh()
        }

        animWaiting.visible()
        animNoData.gone()

        if(recyclerViewShift != null){
            recyclerViewShift.layoutManager = LinearLayoutManager(this)
            shiftsAdapter = ShiftAdapter()
            recyclerViewShift.adapter =shiftsAdapter
            recyclerViewShift.attachFab(fab)
        }
    }

    // force refresh method
    private fun forceRefresh() {
        viewModel.fetchShifts((Calendar.getInstance().time).converted())
    }

    // viewModel methods
    private fun initViewModel(){
        viewModel.shiftListLiveData.observe(this, {
            if(it != null && it.data?.isNotEmpty()!!){
                shiftsAdapter.setUpdatedData(it.data as ArrayList<Shift>)
            } else {
                snackbarError("Something went wrong")
                animWaiting.gone()
                animNoData.visible()
            }
        });

        viewModel.loadingStatus.observe(this, {
            if(it!=null && it.equals(MainViewModel.LoadingStatus.LOADING)){
                swiperefresh.isRefreshing = true
                animWaiting.visible()
                fab.hide()
                shiftsAdapter.setUpdatedData(ArrayList<Shift>())
            } else {
                fab.show()
                animWaiting.gone()
                swiperefresh.isRefreshing = false
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
    private fun addActionListeners(){
        btnLogin.setOnClickListener { startActivity(Intent(this, LoginActivity::class.java))}
        btnSubscribe.setOnClickListener { startActivity(Intent(this, SubscribeActivity::class.java))}
        fab.setOnClickListener {
            snackbarWarning("Oopz, filter feature is not implemented yet")
        }
    }

    override fun onDateSet(view: DatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        txtDate.text = "$year-${(monthOfYear+1).withZero()}-${dayOfMonth.withZero()}"
        forceRefresh()
    }
}