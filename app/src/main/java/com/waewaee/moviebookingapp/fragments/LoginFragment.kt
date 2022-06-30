package com.waewaee.moviebookingapp.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.waewaee.moviebookingapp.R
import com.waewaee.moviebookingapp.delegates.LoginSignUpDelegate
import com.waewaee.moviebookingapp.view.pods.LoginSignUpViewPod
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment(private val loginDelegate: LoginSignUpDelegate) : Fragment() {

    lateinit var loginViewPod: LoginSignUpViewPod

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        loginViewPod.setUpLoginViewPod(loginDelegate)
//        view.btnConfirm.setOnClickListener {
//            requireActivity().startActivity(MovieListActivity.newIntent(requireContext()))
//        }
    }
}