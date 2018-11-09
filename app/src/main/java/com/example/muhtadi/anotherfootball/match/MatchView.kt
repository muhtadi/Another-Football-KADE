package com.example.muhtadi.anotherfootball.match

import com.example.muhtadi.anotherfootball.model.Matches

interface MatchView {
    fun showLoading()
    fun hideLoading()
    fun showMatchList(datas: List<Matches>)
}