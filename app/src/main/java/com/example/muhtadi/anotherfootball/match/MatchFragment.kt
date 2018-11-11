package com.example.muhtadi.anotherfootball.match

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.muhtadi.anotherfootball.R
import com.example.muhtadi.anotherfootball.favorites.FavoritesMatchFragment
import com.example.muhtadi.anotherfootball.favorites.FavoritesTeamFragment
import com.example.muhtadi.anotherfootball.favorites.ViewPagerAdapter

class MatchFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val vPager = view.findViewById<ViewPager>(R.id.match_viewpager)
        val tabs = view.findViewById<TabLayout>(R.id.match_tabs)
        val adapter = ViewPagerAdapter(childFragmentManager)
        adapter.populateFragment(LastMatchFragment(), "Last Match")
        adapter.populateFragment(NextMatchFragment(), "Next Team")
        vPager.adapter = adapter
        tabs.setupWithViewPager(vPager)
    }


}
