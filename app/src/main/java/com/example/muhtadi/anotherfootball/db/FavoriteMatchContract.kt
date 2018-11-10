package com.example.muhtadi.anotherfootball.db

data class FavoriteMatchContract(val id: Long?, val idEvent: String?, val idHomeTeam: String?, val idAwayTeam: String?) {

    companion object {
        const val TABLE_MATCH_FAVORITE: String = "TABLE_MATCH_FAVORITE"
        const val ID: String = "ID_"
        const val MATCH_ID: String = "MATCH_ID"
        const val DATE_MATCH: String = "DATE_MATCH"
        const val HOME_NAME: String = "HOME_NAME"
        const val HOME_SCORE: String = "HOME_SCORE"
        const val AWAY_NAME: String = "AWAY_NAME"
        const val AWAY_SCORE: String = "AWAY_SCORE"

    }
}