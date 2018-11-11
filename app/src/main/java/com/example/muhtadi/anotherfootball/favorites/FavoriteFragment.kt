package com.example.muhtadi.anotherfootball.favorites

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.muhtadi.anotherfootball.R


class FavoriteFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val vPager = view.findViewById<ViewPager>(R.id.fav_viewpager)
        val tabs = view.findViewById<TabLayout>(R.id.fav_tabs)
        val adapter = ViewPagerAdapter(childFragmentManager)
        adapter.populateFragment(FavoritesMatchFragment(), "Favorite Match")
        adapter.populateFragment(FavoritesTeamFragment(), "Favorite Team")
        vPager.adapter = adapter
        tabs.setupWithViewPager(vPager)
    }


}
