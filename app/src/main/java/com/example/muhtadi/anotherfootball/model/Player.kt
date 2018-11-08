package com.example.muhtadi.anotherfootball.model

import com.google.gson.annotations.SerializedName

data class Player(
        @SerializedName("idPlayer")
        var idPlayer: String,

        @SerializedName("strPlayer")
        var strPlayer: String,

        @SerializedName("strDescriptionEN")
        var strDescriptionEN: String,

        @SerializedName("strCutout")
        var strCutout: String

)