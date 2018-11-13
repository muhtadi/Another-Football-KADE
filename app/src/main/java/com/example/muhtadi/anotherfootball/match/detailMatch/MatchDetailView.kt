package com.example.muhtadi.anotherfootball.match.detailMatch

import com.example.muhtadi.anotherfootball.model.Matches
import com.example.muhtadi.anotherfootball.model.Team

interface MatchDetailView {
    fun showLoading()
    fun hideLoading()
    fun showMatchDetail(data: List<Matches>)
    fun showHomeBadge(data: List<Team>)
    fun showAwayBadge(data: List<Team>)
}