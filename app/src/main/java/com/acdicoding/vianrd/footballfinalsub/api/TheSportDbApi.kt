package com.acdicoding.vianrd.footballfinalsub.api

import com.acdicoding.vianrd.footballfinalsub.BuildConfig

object TheSportDbApi {

    fun getLastMatch(idEvent: String?): String{
        return BuildConfig.BASE_URL + "eventspastleague.php?id=" + idEvent
    }

    fun getNextMatch(idEvent: String?): String{
        return BuildConfig.BASE_URL + "eventsnextleague.php?id=" + idEvent
    }

    fun getEventDetails(idEvent: String?): String{
        return BuildConfig.BASE_URL + "lookupevent.php?id=" + idEvent
    }

    fun getTeamDetail(teamId: String?): String{
        return BuildConfig.BASE_URL + "lookupteam.php?id=" + teamId
    }

    fun getPlayerDetail(plId: String?): String{
        return BuildConfig.BASE_URL + "lookupplayer.php?id=" + plId
    }

    fun getAllTeam(league: String?): String{
        return BuildConfig.BASE_URL + "search_all_teams.php?l=" + league
    }

    fun getAllPlayer(teamId: String?): String{
        return BuildConfig.BASE_URL + "lookup_all_players.php?id=" + teamId
    }

}