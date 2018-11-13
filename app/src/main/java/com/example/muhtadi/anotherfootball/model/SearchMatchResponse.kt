package com.example.muhtadi.anotherfootball.model

import com.google.gson.annotations.SerializedName

data class SearchMatchResponse(
        @SerializedName("event")
        val event: List<Matches>
)