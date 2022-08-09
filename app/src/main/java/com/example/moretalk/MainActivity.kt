package com.example.moretalk

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.moretalk.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var navController: LiveData<NavController>? = null

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.materialToolbar)

        if (savedInstanceState == null)
            setUpBottomNav()
    }

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        setUpBottomNav()
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun setUpBottomNav() {
        val graphIds = listOf(
            R.navigation.chat_nav_graph,
            R.navigation.feed_nav_graph,
            R.navigation.profile_nav_graph
        )
        val controller = binding.bottomNavigationView.setupWithNavController(
            graphIds,
            supportFragmentManager,
            R.id.nav_host_fragment,
            intent
        )
        controller.observe(this){
            setupActionBarWithNavController(it)
        }
        navController = controller
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController?.value?.navigateUp()!! || super.onSupportNavigateUp()
    }
}