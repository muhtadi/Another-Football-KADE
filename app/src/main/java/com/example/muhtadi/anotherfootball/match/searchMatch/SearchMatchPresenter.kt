package com.example.muhtadi.anotherfootball.match.searchMatch

import com.example.muhtadi.anotherfootball.api.ApiRepository
import com.example.muhtadi.anotherfootball.api.TheSportDBApi
import com.example.muhtadi.anotherfootball.model.SearchMatchResponse
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.util.*

class SearchMatchPresenter(private val view: SearchMatchView,
                     private val apiRepository: ApiRepository,
                     private val gson: Gson) {

    fun getMatchSearch(teamName: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getMatchSearch(teamName)),
                    SearchMatchResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showMatchList(data.event?: Collections.emptyList())
            }
        }
    }
}