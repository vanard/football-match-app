package com.acdicoding.vianrd.footballfinalsub.model

import com.google.gson.annotations.SerializedName

data class Event (
    @SerializedName("dateEvent") var dateEvent: String,
    @SerializedName("strTime") var timeEvent: String,
    @SerializedName("idAwayTeam") var idAwayTeam: String,
    @SerializedName("idEvent") var idEvent: String,
    @SerializedName("idHomeTeam") var idHomeTeam: String,
    @SerializedName("strEvent") var titleEvent: String,
    @SerializedName("intHomeScore") var homeScore: String?,
    @SerializedName("intAwayScore") var awayScore: String?,
    @SerializedName("intHomeShots") var homeShots: String?,
    @SerializedName("intAwayShots") var awayShots: String?,
    @SerializedName("strHomeTeam") var homeTeam: String?,
    @SerializedName("strAwayTeam") var awayTeam: String?,
    @SerializedName("strAwayGoalDetails") var awayGoalDetails: String?,
    @SerializedName("strAwayLineupDefense") var awayLineupDefense: String?,
    @SerializedName("strAwayLineupForward") var awayLineupForward: String?,
    @SerializedName("strAwayLineupGoalkeeper") var awayLineupGoalkeeper: String?,
    @SerializedName("strAwayLineupMidfield") var awayLineupMidfield: String?,
    @SerializedName("strAwayLineupSubstitutes") var awayLineUpSubstitutes: String?,
    @SerializedName("strHomeGoalDetails") var homeGoalDetails: String?,
    @SerializedName("strHomeLineupDefense") var homeLineupDefense: String?,
    @SerializedName("strHomeLineupForward") var homeLineupForward: String?,
    @SerializedName("strHomeLineupGoalkeeper") var homeLineupGoalkeeper: String?,
    @SerializedName("strHomeLineupMidfield") var homeLineupMidfield: String?,
    @SerializedName("strHomeLineupSubstitutes") var homeLineUpSubstitutes: String?
)