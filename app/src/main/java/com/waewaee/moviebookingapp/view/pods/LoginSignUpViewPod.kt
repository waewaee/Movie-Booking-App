package com.waewaee.moviebookingapp.view.pods

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import com.waewaee.moviebookingapp.delegates.LoginSignUpDelegate
import kotlinx.android.synthetic.main.view_pod_login_sign_up.view.*

class LoginSignUpViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RelativeLayout(context, attrs) {

    lateinit var loginDelegate: LoginSignUpDelegate
    lateinit var signUpDelegate: LoginSignUpDelegate

    override fun onFinishInflate() {
        super.onFinishInflate()
    }

    fun setUpLoginViewPod(delegate: LoginSignUpDelegate) {
        loginDelegate = delegate
        btnConfirm.setOnClickListener {
            loginDelegate.onTapLogin()
        }
    }

    fun setUpSignUpViewPod(delegate: LoginSignUpDelegate) {
        signUpDelegate = delegate
        btnConfirm.setOnClickListener {
            signUpDelegate.onTapSignUp()
        }
    }
}