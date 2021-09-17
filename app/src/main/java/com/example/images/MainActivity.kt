package com.example.images

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.forEach
import androidx.core.view.forEachIndexed
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.images.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

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