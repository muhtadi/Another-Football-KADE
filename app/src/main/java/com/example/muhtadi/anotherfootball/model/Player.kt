package com.example.muhtadi.anotherfootball.model

import com.google.gson.annotations.SerializedName

data class Player(
        @SerializedName("idPlayer")
        var idPlayer: String? = null,

        @SerializedName("strPlayer")
        var strPlayer: String? = null,

        @SerializedName("strDescriptionEN")
        var strDescriptionEN: String? = null,

        @SerializedName("strCutout")
        var strCutout: String? = null

)