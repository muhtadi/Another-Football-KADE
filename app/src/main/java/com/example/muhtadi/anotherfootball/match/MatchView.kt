package com.example.muhtadi.anotherfootball.match

import com.example.muhtadi.anotherfootball.model.Match

interface MatchView {
    fun showLoading()
    fun hideLoading()
    fun showMatchList(data: List<Match>)
}