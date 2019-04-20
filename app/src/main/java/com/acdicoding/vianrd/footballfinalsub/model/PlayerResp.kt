package com.acdicoding.vianrd.footballfinalsub.model

import com.google.gson.annotations.SerializedName

data class PlayerResp(@SerializedName("player") var players: List<Player>)