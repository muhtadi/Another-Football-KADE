package com.example.muhtadi.anotherfootball

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.muhtadi.anotherfootball.R.id.*
import com.example.muhtadi.anotherfootball.R.layout.activity_main
import com.example.muhtadi.anotherfootball.favorites.FavoritesFragment
import com.example.muhtadi.anotherfootball.match.MatchFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main)

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                match -> {
                    loadMatchFragment(savedInstanceState)
                }
                teams -> {
                    loadTeamsFragment(savedInstanceState)
                }
                favorites -> {
                    loadFavoritesFragment(savedInstanceState)
                }
            }
            true
        }
        bottom_navigation.selectedItemId = teams
    }

    private fun loadMatchFragment(savedInstanceState: Bundle?){
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, MatchFragment(), MatchFragment::class.java.simpleName)
                    .commit()
        }
    }

    private fun loadTeamsFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, com.example.muhtadi.anotherfootball.teams.TeamsFragment(), MatchFragment::class.java.simpleName)
                    .commit()
        }
    }

    private fun loadFavoritesFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, FavoritesFragment(), FavoritesFragment::class.java.simpleName)
                    .commit()
        }
    }
}
