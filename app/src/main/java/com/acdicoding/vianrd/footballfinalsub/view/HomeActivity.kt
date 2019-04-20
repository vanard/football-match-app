package com.acdicoding.vianrd.footballfinalsub.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.acdicoding.vianrd.footballfinalsub.R
import com.acdicoding.vianrd.footballfinalsub.R.id.*
import com.acdicoding.vianrd.footballfinalsub.view.favorites.FavoritesFragment
import com.acdicoding.vianrd.footballfinalsub.view.matches.MatchesFragment
import com.acdicoding.vianrd.footballfinalsub.view.teams.TeamsFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                matches -> {
                    loadFragment(savedInstanceState,
                        MatchesFragment()
                    )
                    supportActionBar?.elevation = 0f
                }
                teams -> {
                    loadFragment(savedInstanceState, TeamsFragment())
                    supportActionBar?.elevation = 0f
                }
                favorites -> {
                    loadFragment(savedInstanceState,
                        FavoritesFragment()
                    )
                    supportActionBar?.elevation = 0f
                }
            }
            true
        }
        bottom_navigation.selectedItemId = matches
    }

    private fun loadFragment(savedInstanceState: Bundle?, fm: Fragment) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, fm)
                .commit()
        }
    }

}
