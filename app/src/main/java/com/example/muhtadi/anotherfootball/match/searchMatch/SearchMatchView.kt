package com.example.muhtadi.anotherfootball.match.searchMatch

import com.example.muhtadi.anotherfootball.model.Matches

interface SearchMatchView {
    fun showLoading()
    fun hideLoading()
    fun showMatchList(data: List<Matches>)
}