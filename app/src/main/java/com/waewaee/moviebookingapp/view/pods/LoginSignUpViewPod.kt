package com.waewaee.moviebookingapp.view.pods

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import com.waewaee.moviebookingapp.delegates.ViewPodDelegate
import kotlinx.android.synthetic.main.view_pod_login_sign_up.view.*

class LoginSignUpViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RelativeLayout(context, attrs) {

    lateinit var mDelegate: ViewPodDelegate

    override fun onFinishInflate() {
        super.onFinishInflate()
    }

    fun setUpViewPod(delegate: ViewPodDelegate) {
        mDelegate = delegate
        btnConfirm.setOnClickListener {
            mDelegate.onTapConfirm()
        }
    }

//    fun setUpSignUpViewPod(delegate: LoginSignUpDelegate) {
//        signUpDelegate = delegate
//        btnConfirm.setOnClickListener {
//            signUpDelegate.onTapSignUp()
//        }
//}

}