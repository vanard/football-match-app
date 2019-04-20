package com.acdicoding.vianrd.footballfinalsub.model

import com.google.gson.annotations.SerializedName

data class TeamResp(@SerializedName("teams") var teams: List<Team>)