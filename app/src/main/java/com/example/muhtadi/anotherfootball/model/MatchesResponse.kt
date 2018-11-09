package com.example.muhtadi.anotherfootball.model

import com.google.gson.annotations.SerializedName

data class MatchesResponse(
        @SerializedName("events")
        val matches: List<Matches>
)