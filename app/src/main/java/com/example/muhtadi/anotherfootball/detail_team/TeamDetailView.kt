package com.example.muhtadi.anotherfootball.detail_team

import com.example.muhtadi.anotherfootball.model.Team

interface TeamDetailView {
    fun showLoading()
    fun hideLoading()
    fun showTeamDetail(data: List<Team>)
}