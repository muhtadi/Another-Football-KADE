package com.example.muhtadi.anotherfootball.match

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.widget.SearchView
import android.view.*

import com.example.muhtadi.anotherfootball.R
import com.example.muhtadi.anotherfootball.favorites.ViewPagerAdapter
import com.example.muhtadi.anotherfootball.match.searchMatch.SearchMatchActivity
import org.jetbrains.anko.startActivity

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
        setHasOptionsMenu(true)
        val adapter = ViewPagerAdapter(childFragmentManager)
        adapter.populateFragment(LastMatchFragment(), "Last Match")
        adapter.populateFragment(NextMatchFragment(), "Next Match")
        vPager.adapter = adapter
        tabs.setupWithViewPager(vPager)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.search_menu, menu)

        val searchView = menu?.findItem(R.id.actionSearch)?.actionView as SearchView?

        searchView?.queryHint = "Search matches"

        searchView?.setOnQueryTextListener(object : android.support.v7.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(matchName: String): Boolean {
                context?.startActivity<SearchMatchActivity>("matchName" to matchName)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
    }
}
