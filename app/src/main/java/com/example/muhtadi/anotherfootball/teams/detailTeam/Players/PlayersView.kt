package com.example.muhtadi.anotherfootball.teams.detailTeam.Players

import com.example.muhtadi.anotherfootball.model.Player

interface PlayersView {
    fun showLoading()
    fun hideLoading()
    fun showPlayerList(data: List<Player>)
}