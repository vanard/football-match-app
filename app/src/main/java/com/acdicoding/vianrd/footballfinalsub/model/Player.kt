package com.acdicoding.vianrd.footballfinalsub.model

import com.google.gson.annotations.SerializedName

data class Player (
    @SerializedName("idTeam") var teamId: String? = null,
    @SerializedName("idPlayer") var playerId: String? = null,
    @SerializedName("strPlayer") var playerName: String? = null,
    @SerializedName("strTeam") var playerTeam: String? = null,
    @SerializedName("strDescriptionEN") var playerDesc: String? = null,
    @SerializedName("strPosition") var playerPost: String? = null,
    @SerializedName("strHeight") var playerHeight: String? = null,
    @SerializedName("strWeight") var playerWeight: String? = null,
    @SerializedName("strThumb") var playerThumb: String? = null,
    @SerializedName("strCutout") var playerPic: String? = null

)