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

class LoginFragment() : Fragment(), ViewPodDelegate {

    lateinit var loginViewPod: LoginSignUpViewPod
    private lateinit var loginDelegate: LoginSignUpDelegate

     private var userEmail = ""
     private var userPassword = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        loginDelegate = context as LoginSignUpDelegate
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginViewPod = vpLogin as LoginSignUpViewPod
        loginViewPod.setUpViewPod(this)
//        view.btnConfirm.setOnClickListener {
//            requireActivity().startActivity(MovieListActivity.newIntent(requireContext()))
//        }
    }

    override fun onTapConfirm() {
        if (check()) {
            loginDelegate.onTapLogin(userEmail, userPassword)
        }
    }

    private fun check(): Boolean {
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

        return userEmail.isNotEmpty() && userPassword.isNotEmpty()

    }
}