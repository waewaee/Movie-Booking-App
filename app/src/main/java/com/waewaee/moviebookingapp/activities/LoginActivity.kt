package com.waewaee.moviebookingapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import com.waewaee.moviebookingapp.R
import com.waewaee.moviebookingapp.adapters.LoginSignUpViewPagerAdapter
import com.waewaee.moviebookingapp.delegates.LoginSignUpDelegate
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginSignUpDelegate {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val adapter = LoginSignUpViewPagerAdapter(this, this)
        viewPagerLogin.adapter = adapter

        TabLayoutMediator(tabLayoutLogin, viewPagerLogin) {tab, position ->
            when(position) {
                0 -> tab.text = getString(R.string.lbl_tab_login)
                else -> tab.text = getString(R.string.lbl_tab_sign_up)
            }
        }.attach()
    }

    override fun onTapLogin() {
        startActivity(MovieListActivity.newIntent(this))
    }

    override fun onTapSignUp() {
        startActivity(MovieListActivity.newIntent(this))
    }
}