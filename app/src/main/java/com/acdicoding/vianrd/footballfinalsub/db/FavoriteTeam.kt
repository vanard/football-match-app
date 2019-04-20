package com.acdicoding.vianrd.footballfinalsub.db

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FavoriteTeam (val id: Long?, val idTeam: String, val teamName: String, val teamBadge: String):
    Parcelable {

    companion object {
        const val TABLE_FAV_TEAM : String = "TABLE_FAVORITE_TEAM"
        const val ID: String = "ID"

        const val TEAM_ID: String = "TEAM_ID"
        const val TEAM_NAME: String = "TEAM_NAME"
        const val TEAM_BADGE: String = "TEAM_BADGE"

    }

}