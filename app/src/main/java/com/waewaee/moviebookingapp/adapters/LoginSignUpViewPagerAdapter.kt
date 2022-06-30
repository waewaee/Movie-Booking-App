package com.waewaee.moviebookingapp.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.waewaee.moviebookingapp.delegates.LoginSignUpDelegate
import com.waewaee.moviebookingapp.fragments.LoginFragment
import com.waewaee.moviebookingapp.fragments.SignUpFragment

class LoginSignUpViewPagerAdapter(fragmentActivity: FragmentActivity,
                                  private val loginSignUpDelegate: LoginSignUpDelegate): FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> LoginFragment(loginSignUpDelegate)
            else  -> SignUpFragment(loginSignUpDelegate)
        }
    }
}