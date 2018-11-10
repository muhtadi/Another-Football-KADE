package com.example.muhtadi.anotherfootball.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "Favorite.db", null, 1) {
    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Here you create tables
        db.createTable(FavoriteTeamContract.TABLE_TEAM_FAVORITE, true,
                FavoriteTeamContract.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                FavoriteTeamContract.TEAM_ID to TEXT + UNIQUE,
                FavoriteTeamContract.TEAM_NAME to TEXT,
                FavoriteTeamContract.TEAM_BADGE to TEXT)

        db.createTable(FavoriteMatchContract.TABLE_MATCH_FAVORITE,true,
                FavoriteMatchContract.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                FavoriteMatchContract.MATCH_ID to TEXT + UNIQUE,
                FavoriteMatchContract.DATE_MATCH to TEXT,
                FavoriteMatchContract.HOME_NAME to TEXT,
                FavoriteMatchContract.HOME_SCORE to TEXT,
                FavoriteMatchContract.AWAY_NAME to TEXT,
                FavoriteMatchContract.AWAY_SCORE to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here you can upgrade tables, as usual
        db.dropTable(FavoriteTeamContract.TABLE_TEAM_FAVORITE, true)
        db.dropTable(FavoriteMatchContract.TABLE_MATCH_FAVORITE, true)
    }
}

// Access property for Context
val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)