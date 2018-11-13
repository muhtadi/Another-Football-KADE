package com.example.muhtadi.anotherfootball.teams.detailTeam.TeamOverview

import com.example.muhtadi.anotherfootball.model.Team

interface TeamOverviewView {
    fun showLoading()
    fun hideLoading()
    fun showTeamDetail(data: List<Team>)
}