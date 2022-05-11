package com.example.cat_weather.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.cat_weather.R
import com.example.cat_weather.databinding.FragmentHomeHostBinding

class HomeHostFragment : Fragment() {

    private var _binding: FragmentHomeHostBinding? = null
    private val binding: FragmentHomeHostBinding get() = _binding!!

    private lateinit var appBarConfig: AppBarConfiguration
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeHostBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val navHost = childFragmentManager.findFragmentById(R.id.vh_HomeHost) as NavHostFragment
        navController = navHost.navController
        appBarConfig = AppBarConfiguration(navController.graph, binding.drawer)

        NavigationUI.setupWithNavController(binding.navigationView, navController)
        NavigationUI.setupWithNavController(binding.toolbar, navController, appBarConfig)
    }

}