package com.example.muhtadi.anotherfootball.teams.detailTeam.Players

import com.example.muhtadi.anotherfootball.api.ApiRepository
import com.example.muhtadi.anotherfootball.api.TheSportDBApi
import com.example.muhtadi.anotherfootball.model.PlayerResponse
import com.example.muhtadi.anotherfootball.model.TeamResponse
import com.example.muhtadi.anotherfootball.teams.TeamsView
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.util.*

class PlayersPresenter(private val view: PlayersView,
                     private val apiRepository: ApiRepository,
                     private val gson: Gson) {

    fun getPlayerList(teamId: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getAllPlayer(teamId)),
                    PlayerResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showPlayerList(data.player ?: Collections.emptyList())
            }
        }
    }
}