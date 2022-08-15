package com.waewaee.moviebookingapp.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.waewaee.moviebookingapp.R
import com.waewaee.moviebookingapp.delegates.LoginSignUpDelegate
import com.waewaee.moviebookingapp.delegates.ViewPodDelegate
import com.waewaee.moviebookingapp.view.pods.LoginSignUpViewPod
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_sign_up.*
import kotlinx.android.synthetic.main.fragment_sign_up.etEmail
import kotlinx.android.synthetic.main.fragment_sign_up.etPassword

class SignUpFragment() : Fragment(), ViewPodDelegate {

    lateinit var signUpViewPod: LoginSignUpViewPod
    private lateinit var signUpDelegate: LoginSignUpDelegate

    private var userName = ""
    private var userEmail = ""
    private var userPassword = ""
    private var userPhone  = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        signUpDelegate = context as LoginSignUpDelegate
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        signUpViewPod = vpSignUp as LoginSignUpViewPod
        signUpViewPod.setUpViewPod(this)
//        view.btnConfirm.setOnClickListener {
//            requireActivity().startActivity(MovieListActivity.newIntent(requireActivity().applicationContext))
//        }
    }

    override fun onTapConfirm() {
        if (check()) {
            signUpDelegate.onTapSignUp(userName, userEmail, userPassword, userPhone)
        }
    }

    private fun check(): Boolean {
        if (etName.text.isNullOrBlank()) {
            etName.setError("Cannot be empty!")
        } else  {
            userName = etName.text.toString()
        }

        if (etEmail.text.isNullOrBlank()) {
            etEmail.setError("Cannot be empty!")
        } else  {
            userEmail = etEmail.text.toString()
        }

        if (etPassword.text.isNullOrBlank()) {
            etPassword.setError("Cannot be empty!")
        } else {
            userPassword = etPassword.text.toString()
        }

        if (etPhone.text.isNullOrBlank()) {
            etPhone.setError("Cannot be empty!")
        } else  {
            userPhone = etPhone.text.toString()
        }

        return userName.isNotEmpty() && userEmail.isNotEmpty() && userPassword.isNotEmpty() && userPhone.isNotEmpty()

    }

}