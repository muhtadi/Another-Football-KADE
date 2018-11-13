package com.example.muhtadi.anotherfootball.model

import android.os.Parcel
import android.os.Parcelable
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
): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(idTeam)
        parcel.writeString(strTeam)
        parcel.writeString(strTeamBadge)
        parcel.writeString(intFormedYear)
        parcel.writeString(strStadium)
        parcel.writeString(strDescriptionEN)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Team> {
        override fun createFromParcel(parcel: Parcel): Team {
            return Team(parcel)
        }

        override fun newArray(size: Int): Array<Team?> {
            return arrayOfNulls(size)
        }
    }
}