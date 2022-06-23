package com.waewaee.moviebookingapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import com.waewaee.moviebookingapp.R
import com.waewaee.moviebookingapp.adapters.LoginSignUpViewPagerAdapter
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val adapter = LoginSignUpViewPagerAdapter(this)
        viewPagerLogin.adapter = adapter

        TabLayoutMediator(tabLayoutLogin, viewPagerLogin) {tab, position ->
            when(position) {
                0 -> tab.text = "Login"
                else -> tab.text = "Sign Up"
            }
        }.attach()
    }
}