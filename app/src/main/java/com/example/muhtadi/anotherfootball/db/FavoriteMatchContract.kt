package com.example.muhtadi.anotherfootball.db

data class FavoriteMatchContract(
        val id: Long?,
        val matchId: String?,
        val dateEvent: String?,
        val homeId: String?,
        val homeTeam: String?,
        val homeScore: String?,
        val awayId: String?,
        val awayTeam: String?,
        val awayScore: String?) {

    companion object {
        const val TABLE_MATCH_FAVORITE: String = "TABLE_MATCH_FAVORITE"
        const val ID: String = "ID_"
        const val MATCH_ID: String = "MATCH_ID"
        const val DATE_EVENT: String = "DATE_EVENT"
        const val HOME_ID: String = "HOME_ID"
        const val HOME_TEAM: String = "HOME_TEAM"
        const val HOME_SCORE: String = "HOME_SCORE"
        const val AWAY_ID: String = "AWAY_ID"
        const val AWAY_TEAM: String = "AWAY_TEAM"
        const val AWAY_SCORE: String = "AWAY_SCORE"
    }
}