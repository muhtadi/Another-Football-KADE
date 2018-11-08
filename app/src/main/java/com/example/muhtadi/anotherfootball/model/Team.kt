package com.example.muhtadi.anotherfootball.model

import com.google.gson.annotations.SerializedName

data class Team(
        @SerializedName("idTeam")
        var idTeam: String? = null,

        @SerializedName("strTeam")
        var strTeam: String? = null,

        @SerializedName("strTeamBadge")
        var strTeamBadge: String? = null,

        @SerializedName("intFormedYear")
        var intFormedYear: String? = null,

        @SerializedName("strStadium")
        var strStadium: String? = null,

        @SerializedName("strDescriptionEN")
        var strDescriptionEN: String? = null
)