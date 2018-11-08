package com.example.muhtadi.anotherfootball.model

import com.google.gson.annotations.SerializedName

data class Match(

        @SerializedName("idEvent")
        var idEvent: String,

        @SerializedName("idAwayTeam")
        var idAwayTeam: String,
        @SerializedName("strAwayTeam")
        var strAwayTeam: String,
        @SerializedName("intAwayScore")
        var intAwayScore: String,
        @SerializedName("strAwayGoalDetails")
        var strAwayGoalDetails: String,

        @SerializedName("idHomeTeam")
        var idHomeTeam: String,
        @SerializedName("strHomeTeam")
        var strHomeTeam: String,
        @SerializedName("intHomeScore")
        var intHomeScore: String,
        @SerializedName("strHomeGoalDetails")
        var strHomeGoalDetails: String

)
