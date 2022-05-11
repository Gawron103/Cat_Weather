package com.example.cat_weather.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cat_weather.databinding.FragmentAuthHostBinding
import com.example.cat_weather.views.adapters.AuthenticationAdapter
import com.google.android.material.tabs.TabLayoutMediator

class AuthHostFragment : Fragment() {

    private var _binding: FragmentAuthHostBinding? = null
    private val binding: FragmentAuthHostBinding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val authFragments = arrayListOf(
            LoginFragment(),
            RegisterFragment()
        )

        val adapter = AuthenticationAdapter(
            authFragments,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        binding.vpAuthenticationPager.adapter = adapter

        TabLayoutMediator(binding.tlAuthenticationTabs, binding.vpAuthenticationPager) { tab, pos ->
            tab.text = if (pos == 0) "Login" else "Signup"
        }.attach()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAuthHostBinding.inflate(inflater, container, false)
        return binding.root
    }

}