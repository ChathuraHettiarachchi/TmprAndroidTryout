package com.example.temper.ui.subscribe

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.temper.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.toolbar_normal.*

@AndroidEntryPoint
class SubscribeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subscribe)

        txtTitle.text = "Subscribe"
        btnBack.setOnClickListener{onBackPressed()}
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}