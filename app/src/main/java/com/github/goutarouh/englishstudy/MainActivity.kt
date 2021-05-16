package com.github.goutarouh.englishstudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initBottomNavigationView()
    }

    private fun initBottomNavigationView() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        NavigationUI.setupWithNavController(bottomNavigationView, navController)

        // ナビゲーションで兄弟要素がある場合はsetで指定する。
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.bottom_navigation_check,
                R.id.checkSentencesFragment,
                R.id.checkSentencesEndFragment,
                R.id.bottom_navigation_list
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    /**
     * アプリバーの戻るボタンを有効にする。
     */
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

}