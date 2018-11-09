package com.example.muhtadi.anotherfootball.match

import com.example.muhtadi.anotherfootball.model.Match
import com.example.muhtadi.anotherfootball.model.Team

interface MatchView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Team>)
}