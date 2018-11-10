package com.example.muhtadi.anotherfootball.detailMatch

import com.example.muhtadi.anotherfootball.model.Matches

interface MatchDetailView {
    fun showLoading()
    fun hideLoading()
    fun showMatchDetail(data: List<Matches>)
}