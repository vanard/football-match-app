package com.acdicoding.vianrd.footballfinalsub.model

import com.google.gson.annotations.SerializedName

data class Team(
    @SerializedName("idTeam") var teamId: String? = null,
    @SerializedName("strTeam") var teamName: String? = null,
    @SerializedName("strTeamBadge") var teamBadge: String? = null,
    @SerializedName("strLeague") var leagueName: String? = null,
    @SerializedName("strDescriptionEN") var teamDescriptionENG: String? = null,
    @SerializedName("intFormedYear") var teamFormedYear: String? = null,
    @SerializedName("strStadium") var teamStadium: String? = null,
    @SerializedName("idLeague") var leagueId: String? = null
)