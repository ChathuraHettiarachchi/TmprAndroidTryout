package com.example.temper.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.temper.R
import kotlinx.android.synthetic.main.toolbar_normal.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        txtTitle.text = "Login"
        btnBack.setOnClickListener{onBackPressed()}
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}