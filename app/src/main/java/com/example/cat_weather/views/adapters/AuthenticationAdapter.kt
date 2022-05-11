package com.example.cat_weather.views.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class AuthenticationAdapter(
    fragments: ArrayList<Fragment>,
    fm: FragmentManager,
    lifecycle: Lifecycle
): FragmentStateAdapter(fm, lifecycle) {

    private val authenticationFragments = fragments

    override fun getItemCount(): Int = authenticationFragments.size

    override fun createFragment(position: Int): Fragment {
        return authenticationFragments[position]
    }
}