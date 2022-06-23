package com.waewaee.moviebookingapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.waewaee.moviebookingapp.R
import com.waewaee.moviebookingapp.activities.LoginActivity
import com.waewaee.moviebookingapp.activities.MovieListActivity
import kotlinx.android.synthetic.main.view_pod_login_sign_up.view.*

class LoginFragment : Fragment() {

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
        view.btnConfirm.setOnClickListener {
            requireActivity().startActivity(MovieListActivity.newIntent(requireContext()))
        }
    }
}