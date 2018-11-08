package com.example.muhtadi.anotherfootball.match

import com.example.muhtadi.anotherfootball.api.ApiRepository
import com.example.muhtadi.anotherfootball.api.TheSportDBApi
import com.example.muhtadi.anotherfootball.model.MatchResponse
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.util.*

class MatchPresenter(private val view: MatchView?,
                     private val apiRepository: ApiRepository,
                     private val gson: Gson) {

    fun getLastMatch(league: String = "4328") {
        view!!.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getLastMatch(league)),
                    MatchResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showMatchList(data.match)
            }

        }
    }
}