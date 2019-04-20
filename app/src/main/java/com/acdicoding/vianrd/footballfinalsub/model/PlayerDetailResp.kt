package com.acdicoding.vianrd.footballfinalsub.model

import com.google.gson.annotations.SerializedName

data class PlayerDetailResp(@SerializedName("players") var players: List<Player>)