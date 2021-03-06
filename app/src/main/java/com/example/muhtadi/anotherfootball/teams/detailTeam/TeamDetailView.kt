package com.example.muhtadi.anotherfootball.teams.detailTeam

import com.example.muhtadi.anotherfootball.model.Team

interface TeamDetailView {
    fun showLoading()
    fun hideLoading()
    fun showTeamDetail(data: List<Team>)
}