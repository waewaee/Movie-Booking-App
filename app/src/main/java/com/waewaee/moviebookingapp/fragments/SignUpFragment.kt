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
import kotlinx.android.synthetic.main.fragment_sign_up.*

class SignUpFragment(private val signUpDelegate: LoginSignUpDelegate) : Fragment() {

    lateinit var signUpViewPod: LoginSignUpViewPod

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        signUpViewPod.setUpSignUpViewPod(signUpDelegate)
//        view.btnConfirm.setOnClickListener {
//            requireActivity().startActivity(MovieListActivity.newIntent(requireActivity().applicationContext))
//        }
    }

}