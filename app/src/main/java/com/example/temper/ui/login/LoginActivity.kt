package com.example.temper.ui.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.temper.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.toolbar_normal.*

@AndroidEntryPoint
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