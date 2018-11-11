package com.example.muhtadi.anotherfootball.match

import com.example.muhtadi.anotherfootball.api.ApiRepository
import com.example.muhtadi.anotherfootball.api.TheSportDBApi
import com.example.muhtadi.anotherfootball.model.MatchesResponse
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MatchPresenter(private val view: MatchView,
                     private val apiRepository: ApiRepository,
                     private val gson: Gson) {

    fun getLastMatchList(leagueId: String?) {
        view.showLoading()
        doAsync {
            val datas = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getLastMatch(leagueId)),
                    MatchesResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showMatchList(datas.matches)
            }
        }
    }

    fun getNextMatchList(leagueId: String?) {
        view.showLoading()
        doAsync {
            val datas = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getNextMatch(leagueId)),
                    MatchesResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showMatchList(datas.matches)
            }
        }
    }
}