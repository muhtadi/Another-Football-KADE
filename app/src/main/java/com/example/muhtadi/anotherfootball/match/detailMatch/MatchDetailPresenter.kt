package com.example.muhtadi.anotherfootball.match.detailMatch

import com.example.muhtadi.anotherfootball.api.ApiRepository
import com.example.muhtadi.anotherfootball.api.TheSportDBApi
import com.example.muhtadi.anotherfootball.model.MatchesResponse
import com.example.muhtadi.anotherfootball.model.TeamResponse
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MatchDetailPresenter(private val view: MatchDetailView,
                          private val apiRepository: ApiRepository,
                          private val gson: Gson) {

    fun getMatchDetail(id: String) {
        view.showLoading()
        doAsync{
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getMatch(id)),
                    MatchesResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showMatchDetail(data.matches)
            }
        }
    }

    fun getHomeBadge(id: String) {
        view.showLoading()
        doAsync{
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getTeamDetail(id)),
                    TeamResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showHomeBadge(data.teams)
            }
        }
    }

    fun getAwayBadge(id: String) {
        view.showLoading()
        doAsync{
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getTeamDetail(id)),
                    TeamResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showAwayBadge(data.teams)
            }
        }
    }
}