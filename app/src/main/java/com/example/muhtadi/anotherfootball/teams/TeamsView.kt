package com.example.muhtadi.anotherfootball.teams

import com.example.muhtadi.anotherfootball.model.Team

interface TeamsView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Team>)
}