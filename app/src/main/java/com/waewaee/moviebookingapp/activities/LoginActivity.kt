package com.waewaee.moviebookingapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import com.waewaee.moviebookingapp.R
import com.waewaee.moviebookingapp.adapters.LoginSignUpViewPagerAdapter
import com.waewaee.moviebookingapp.data.models.CinemaModel
import com.waewaee.moviebookingapp.data.models.CinemaModelImpl
import com.waewaee.moviebookingapp.delegates.LoginSignUpDelegate
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity() : AppCompatActivity(), LoginSignUpDelegate {

    private val mCinemaModel: CinemaModel = CinemaModelImpl

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setUpViewPager()
        setUpTapLayout()
    }

    private fun setUpTapLayout() {
        TabLayoutMediator(tabLayoutLogin, viewPagerLogin) {tab, position ->
            when(position) {
                0 -> tab.text = getString(R.string.lbl_tab_login)
                else -> tab.text = getString(R.string.lbl_tab_sign_up)
            }
        }.attach()
    }

    private fun setUpViewPager() {
        val adapter = LoginSignUpViewPagerAdapter(this, this)
        viewPagerLogin.adapter = adapter
    }

    override fun onTapLogin(userEmail: String, userPassword: String) {
        requestLoginData(userEmail, userPassword)
    }

    override fun onTapSignUp(userName: String, userEmail: String, userPassword: String, userPhone: String) {
        requestSignUpData(userName, userEmail, userPassword, userPhone)
    }

    private fun requestLoginData(email: String, password: String) {
        mCinemaModel.getLoginWithEmail(
            email = email,
            password = password,
            onSuccess = {
                if (it.code == 200) {
//                    Snackbar.make(window.decorView, "${it.message}", Snackbar.LENGTH_SHORT).show()
                    startActivity(MovieListActivity.newIntent(this))
                    finish()
                } else {
                    Snackbar.make(window.decorView, "${it.message}", Snackbar.LENGTH_SHORT).show()
                }
            },
            onFailure = {
                Snackbar.make(window.decorView, "$it.", Snackbar.LENGTH_SHORT).show()
            }
        )
    }

    private fun requestSignUpData(name: String, email: String, password: String, phone: String) {
        mCinemaModel.getSignUpWithEmail(
            name = name,
            email = email,
            password = password,
            phone = phone,
            onSuccess = {
                if (it.code == 201) {
//                    Snackbar.make(window.decorView, "${it.message}", Snackbar.LENGTH_SHORT).show()
                    startActivity(MovieListActivity.newIntent(this))
                    finish()
                } else {
                    Snackbar.make(window.decorView, "${it.message}", Snackbar.LENGTH_SHORT).show()
                }
            },
            onFailure = {
                Snackbar.make(window.decorView, "$it.", Snackbar.LENGTH_SHORT).show()
            }
        )
    }
}