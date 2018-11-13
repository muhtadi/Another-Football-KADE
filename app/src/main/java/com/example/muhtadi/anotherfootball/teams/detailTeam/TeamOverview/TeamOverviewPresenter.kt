package com.example.muhtadi.anotherfootball.teams.detailTeam.TeamOverview

import com.example.muhtadi.anotherfootball.api.ApiRepository
import com.example.muhtadi.anotherfootball.api.TheSportDBApi
import com.example.muhtadi.anotherfootball.model.TeamResponse
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.util.*

class TeamOverviewPresenter(private val view: TeamOverviewView,
                          private val apiRepository: ApiRepository,
                          private val gson: Gson) {

    fun getTeamDetail(teamId: String?) {
        view.showLoading()
        doAsync{
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getTeamDetail(teamId)),
                    TeamResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showTeamDetail(data.teams?: Collections.emptyList())
            }
        }
    }
}