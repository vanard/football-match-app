package com.acdicoding.vianrd.footballfinalsub.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class DatabaseHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "Favorite.db", null, 1){

    companion object {
        private var instance: DatabaseHelper? = null

        @Synchronized
        fun getInstance(ctx: Context) : DatabaseHelper{
            if (instance == null){
                instance = DatabaseHelper(ctx.applicationContext)
            }
            return instance as DatabaseHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(FavoriteMatch.TABLE_FAV_MATCH, true,
            FavoriteMatch.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            FavoriteMatch.EVENT_ID to TEXT + UNIQUE,
            FavoriteMatch.HOME_TEAM_NAME to TEXT,
            FavoriteMatch.AWAY_TEAM_NAME to TEXT,
            FavoriteMatch.HOME_SCORE to TEXT,
            FavoriteMatch.AWAY_SCORE to TEXT,
            FavoriteMatch.EVENT_DATE to TEXT,
            FavoriteMatch.EVENT_TIME to TEXT)

        db?.createTable(FavoriteTeam.TABLE_FAV_TEAM, true,
            FavoriteTeam.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            FavoriteTeam.TEAM_ID to TEXT + UNIQUE,
            FavoriteTeam.TEAM_NAME to TEXT,
            FavoriteTeam.TEAM_BADGE to TEXT)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(FavoriteMatch.TABLE_FAV_MATCH, true)
        db?.dropTable(FavoriteTeam.TABLE_FAV_TEAM, true)
    }

}

val Context.dab : DatabaseHelper
    get() = DatabaseHelper.getInstance(applicationContext)