package com.example.myapplication.home.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.myapplication.R

class MainActivity : AppCompatActivity() {
    private var navHostFragment: NavHostFragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_container) as NavHostFragment

        navHostFragment.navController.addOnDestinationChangedListener { _, destination, _ ->
            supportActionBar!!.title = destination.label
            when (destination.id) {
                R.id.news_detail_fragment -> {
                    supportActionBar!!.setDisplayHomeAsUpEnabled(true)
                }
                else -> supportActionBar!!.setDisplayHomeAsUpEnabled(false)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                navHostFragment?.navController?.popBackStack()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}