package com.example.images.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.forEach
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.images.R
import com.example.images.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        //Setup a custom top menu to have a home button
        setSupportActionBar(binding.topAppBar)

        //By default there will be no active bottom nav items.
        navView.uncheckAllItems()

        binding.topAppBar.setNavigationOnClickListener {
            navController.navigateUp() //It goes to the Home fragment
            //When going to home fragment, uncheck the active bottom nav items
            navView.uncheckAllItems()
        }

        navView.setupWithNavController(navController)
    }
}

fun BottomNavigationView.uncheckAllItems() {
    menu.setGroupCheckable(0, true, false)
    menu.forEach {
        it.isChecked = false
    }
    menu.setGroupCheckable(0, true, true)
}