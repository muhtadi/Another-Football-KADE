package com.example.muhtadi.anotherfootball.model

import com.google.gson.annotations.SerializedName

data class Matches(

        @SerializedName("idEvent")
        var idEvent: String? = null,

        @SerializedName("idAwayTeam")
        var idAwayTeam: String? = null,
        @SerializedName("strAwayTeam")
        var strAwayTeam: String? = null,
        @SerializedName("intAwayScore")
        var intAwayScore: String? = null,
        @SerializedName("strAwayGoalDetails")
        var strAwayGoalDetails: String? = null,

        @SerializedName("idHomeTeam")
        var idHomeTeam: String? = null,
        @SerializedName("strHomeTeam")
        var strHomeTeam: String? = null,
        @SerializedName("intHomeScore")
        var intHomeScore: String? = null,
        @SerializedName("strHomeGoalDetails")
        var strHomeGoalDetails: String? = null

)
